package com.fourthgroup.projectmanageman.controller;

import com.fourthgroup.projectmanageman.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Set;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Nov 29, 2021
    ===============================
 */

@Controller
public class ProjectController {

    @Autowired
    ProjectService service;

    @GetMapping("/project")
    public String showProject(HttpSession session, @RequestParam int projectID, Model model){
        if (service.authorize(session, projectID)){
            model.addAttribute(service.getGetProject(projectID));
            return "projectTemplate";
        } else {
            return "redirect:/userNotAuthorised";
        }
    }

    @GetMapping("/project")
    public String createProjectSuccess(HttpSession session, Model model){
        model.addAttribute(session.getAttribute("project"));
        session.removeAttribute("project");
        return "projectTemplate";
    }

    @GetMapping("/new_project")
    public String createNewProject(HttpSession session){
        service.createNewProject(session);
        return "createProjectForm";
    }

    @PostMapping("/new_project")
    public String createNewProject(WebRequest project, HttpSession session){
        session.setAttribute("project",service.submitProjectForm(project));
        return "redirect:/add_participants";
    }

    @GetMapping("/add_participants")
    public String showUsers(HttpSession session, Model model){
        model.addAttribute("users", service.showUsers());
        return "selectUsers";
    }

    @PostMapping("/add_participants")
    public String selectUsers(HttpSession session, @ModelAttribute Set<User> selectedParticipants){

    }
}
