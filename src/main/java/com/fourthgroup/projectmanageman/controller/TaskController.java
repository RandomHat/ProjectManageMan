package com.fourthgroup.projectmanageman.controller;

import com.fourthgroup.projectmanageman.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class TaskController {
    TaskService service;

    @GetMapping("/project/new-task")
    public String createTaskGET(){
        return "createTaskForm";
    }

    @PostMapping("/project/new-task")
    public String createTaskPOST(WebRequest taskForm){
        service.createTask(taskForm);
        return "createTaskSuccess";
    }

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }
}
