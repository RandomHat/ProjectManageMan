package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class TaskService {

    TaskRepository repository;

    public Task createTask(WebRequest taskForm){
        Task task = new Task();

        return task;
    }

    @Autowired
    public void setRepository(TaskRepository repository){
        this.repository = repository;
    }
}
