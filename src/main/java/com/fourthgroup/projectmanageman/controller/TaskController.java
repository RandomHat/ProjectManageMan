package com.fourthgroup.projectmanageman.controller;

import com.fourthgroup.projectmanageman.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Dec 11, 2021
    ===============================
 */

@Controller
public class TaskController {
    TaskService service;

    @GetMapping("/project/{projectID}/new-task")
    public String createTaskGET(@PathVariable("projectID") int projectID, Model model, HttpSession session){
        model.addAttribute("projectID",projectID);
        //Authorise User
        return "CreateTask";
    }

    @PostMapping("/project/{projectID}/new-task")
    public RedirectView createTaskPOST(@PathVariable("projectID") int projectID, WebRequest taskForm, HttpSession session){
        if (service.createTask(taskForm, projectID)) {
            return new RedirectView("/project/" + projectID);
        } else {
            return new RedirectView("/project/" + projectID +"/new-task");
        }
    }

    @GetMapping("/project/{projectID}/{parentTaskID}/new-sub-task")
    public String createSubTaskGET(@PathVariable int projectID, @PathVariable int parentTaskID, HttpSession session){
        return "CreateTask";

    }

    @PostMapping("/project/{projectID}/{parentTaskID}/new-task")
    public RedirectView createSubTaskPOST(@PathVariable int projectID, @PathVariable int parentTaskID, WebRequest taskForm, HttpSession session){

        if (service.createTask(taskForm, projectID, parentTaskID)) {
            return new RedirectView("/project/" + projectID + "/" + parentTaskID);
        } else {
            return new RedirectView("/project/" + projectID + "/" + parentTaskID + "/new-task");
        }
    }

    @GetMapping("/project/{projectID}/{taskID}")
    public String taskGET(@PathVariable int projectID, @PathVariable int taskID, Model model){
        //service.getTask(taskID)
        return "getTask";
    }

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }
}
