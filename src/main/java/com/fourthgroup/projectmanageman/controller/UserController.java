package com.fourthgroup.projectmanageman.controller;


import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.service.ChartService;
import com.fourthgroup.projectmanageman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Dec 3, 2021
    ===============================
 */

@Controller
public class UserController {

    UserService userService;
    ChartService chartService;

    @Autowired
    public void setChartService(ChartService chartService){
        this.chartService = chartService;
    }
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/create-account")
    public String createAccountView(HttpSession session){
        return "CreateAccount";
    }

    @PostMapping("/create-account")
    public String createAccountForm(WebRequest requestFromUser){

        if(userService.samePassword(requestFromUser)){
            if(userService.submitAccountDetails(requestFromUser)) {
                return "redirect:/";
            }
        }
        return "redirect:/WrongAccountInfo";
    }

    @GetMapping("/")
    public String loginView(HttpSession session){
        return "Login";
    }

    @PostMapping("/Login")
    public String loginForm(WebRequest requestFromUser, HttpSession session){
        User user = userService.loginApproved(requestFromUser);
        if(0 < user.getId()){
            session.setAttribute("user",user);
            return "redirect:/user-panel";
        }
        return "redirect:/";
    }

    @GetMapping("/user-panel")
    public String userPanelView(HttpSession session, Model model){
        List<Project> userProjectlist = userService.userProjectList(session);
        List<Task> userTaskList = userService.userTaskList(session);
        model.addAttribute("projects",userProjectlist);
        model.addAttribute("tasks",userTaskList);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("projectChart",chartService.projectChart(userProjectlist));
        model.addAttribute("taskChart",chartService.taskChart(userTaskList));

        return "/user-panel";
    }

}
