package com.fourthgroup.projectmanageman.controller;

import com.fourthgroup.projectmanageman.model.Task;
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
import java.util.Set;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Dec 11, 2021
    ===============================
 */

@Controller
public class TaskController {
    TaskService service;

    @PostMapping("/project/{projectID}/new-task")
    public String createTaskPOST(@PathVariable("projectID") int projectID, WebRequest taskForm, Model model, HttpSession session){
        model.addAttribute(projectID);
        model.addAttribute("isParent", true);
        if (service.createTask(taskForm, projectID)) {
            return "redirect:/project/" + projectID;
        } else {
            return "CreateTaskError";
        }
    }

    @PostMapping("/project/{projectID}/{parentTaskID}/new-task")
    public String createSubTaskPOST(@PathVariable int projectID, @PathVariable int parentTaskID, Model model, WebRequest taskForm, HttpSession session){
        model.addAttribute(projectID);
        model.addAttribute(parentTaskID);
        if (service.createTask(taskForm, projectID, parentTaskID)) {
            return "redirect:/project/" + projectID + "/" + parentTaskID;
        } else {
            return "CreateTaskError";
        }
    }

    @GetMapping("/project/{projectID}/{parentTaskID}")
    public String subTaskGET(@PathVariable int projectID, @PathVariable int parentTaskID, Model model){
        //service.getTask(taskID)
        model.addAttribute(projectID);
        model.addAttribute(parentTaskID);
        model.addAttribute("emptyTask",new Task());
        model.addAttribute("isParent", false);
        model.addAttribute("tasks", service.getSubTasks(parentTaskID).toArray());
        return "subTasks";
    }

    @PostMapping("/project/{projectID}/{taskID}/update")
    public String updateTaskPOST(@PathVariable int projectID, @PathVariable int taskID, Model model, WebRequest taskForm, HttpSession session){
        if(service.update(taskForm, taskID)){
            return "redirect:/project/" + projectID;
        }
        return "WrongAccountInfo";
    }

    @PostMapping("/project/{projectID}/{taskID}/delete")
    public String deleteTaskPOST(@PathVariable int projectID,@PathVariable int taskID, Model model, HttpSession session) {
        service.delete(taskID);
        return "redirect:/project/" + projectID;
    }

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }
}
