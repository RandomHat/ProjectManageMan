package com.fourthgroup.projectmanageman.controller;


import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;

/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Nov 3, 2021
    ===============================
 */

@Controller
public class UserController {

    UserService userService;

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
        model.addAttribute("projects",userService.userProjectList(session));
        model.addAttribute("tasks",userService.userTaskList(session));
        model.addAttribute("user",session.getAttribute("user"));
        //model.addAttribute("deadlineList",userService.userDeadlineList(session));
        return "/user-panel";
    }

}
