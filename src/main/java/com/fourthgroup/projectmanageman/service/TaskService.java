package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.Status;
import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

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
        Task task = parseTask(taskForm, new Task());
        task.setProjectID(projectID);

        return repository.write(task) != -1;
    }

    public boolean createTask(WebRequest taskForm, int projectID, int parentTaskID){
        Task task = parseTask(taskForm, new Task());
        task.setProjectID(projectID);
        task.setParentTaskID(parentTaskID);

        return repository.write(task) != -1;
    }

    public Collection<Task> getTasks(int projectID){return repository.getProjectTasks(projectID);}

    public Collection<Task> getSubTasks(int parentTaskID){
        return repository.getSubTasks(parentTaskID);
    }

    public boolean delete(int taskID){
        return repository.delete(new Task(taskID));
    }

    public boolean update(WebRequest taskForm, int taskID){
        Task task = parseTask(taskForm, new Task(taskID));
        return repository.update(task);
    }

    private Task parseTask(WebRequest taskForm, Task task){
        task.setTitle(taskForm.getParameter("title"));

        task.setProjectID(Integer.parseInt(taskForm.getParameter("project-id")));
        task.setParentTaskID(Integer.parseInt(taskForm.getParameter("parent-task-id")));

        String statusRaw = taskForm.getParameter("status");
        Status status = "".equals(statusRaw)  ? null : Status.fromInteger(Integer.parseInt(statusRaw));
        task.setStatus(status);

        task.setType(taskForm.getParameter("type"));
        task.setDescription(taskForm.getParameter("description"));
        task.setProductDescription(taskForm.getParameter("product-description"));

        task.setEstTime(Integer.parseInt(taskForm.getParameter("est-time")));
        task.setSpentTime(Integer.parseInt(taskForm.getParameter("spent-time")));

        String deadlineRaw = taskForm.getParameter("deadline");
        LocalDate deadline = "".equals(deadlineRaw) || deadlineRaw == null ? null : LocalDate.parse(deadlineRaw);
        task.setDeadline(deadline);

        String startDateRaw = taskForm.getParameter("start-date");
        LocalDate startDate = "".equals(startDateRaw) || startDateRaw == null ? null : LocalDate.parse(startDateRaw);
        task.setStartDate(startDate);


        return task;
    }


    @Autowired
    public void setRepository(TaskRepository repository){
        this.repository = repository;
    }
}
