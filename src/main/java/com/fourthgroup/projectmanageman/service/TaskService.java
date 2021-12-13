package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.repository.TaskRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.Objects;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Dec 11, 2021
    ===============================
 */

@Service
public class TaskService {

    TaskRepository repository;

    public boolean createTask(WebRequest taskForm, int projectID){
        Task task = createTask(taskForm);
        task.setProjectID(projectID);

        return repository.write(task) != -1;
    }

    public boolean createTask(WebRequest taskForm, int projectID, int parentTaskID){
        Task task = createTask(taskForm);
        task.setProjectID(projectID);
        task.setParentTaskID(parentTaskID);

        return repository.write(task) != -1;
    }

    private Task createTask(WebRequest taskForm){
        Task task = new Task();
        task.setTitle(taskForm.getParameter("title"));
        task.setType(taskForm.getParameter("type"));
        task.setDescription(taskForm.getParameter("description"));
        task.setProductDescription(taskForm.getParameter("product-description"));
        task.setEstTime(Integer.parseInt(Objects.requireNonNull(taskForm.getParameter("est-time"))));

        String startDateRaw = taskForm.getParameter("start-date");
        LocalDate startDate = "".equals(startDateRaw) ? null : LocalDate.parse(startDateRaw);
        task.setStartDate(startDate);

        String deadlineRaw = taskForm.getParameter("deadline");
        LocalDate deadline = "".equals(deadlineRaw) ? null : LocalDate.parse(deadlineRaw);
        task.setDeadline(deadline);
        return task;
    }


    @Autowired
    public void setRepository(TaskRepository repository){
        this.repository = repository;
    }
}
