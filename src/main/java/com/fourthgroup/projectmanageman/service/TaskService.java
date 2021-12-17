package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.Status;
import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Locale;
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

        String parentTaskIDRaw = taskForm.getParameter("parent-task-id");
        Integer parentTaskID = "".equals(parentTaskIDRaw) || parentTaskIDRaw == null ? null : Integer.parseInt(parentTaskIDRaw);
        task.setParentTaskID(parentTaskID);

        String projectIDRaw = taskForm.getParameter("project-id");
        Integer projectID = "".equals(projectIDRaw) || projectIDRaw == null ? null : Integer.parseInt(projectIDRaw);
        task.setProjectID(projectID);

        String statusRaw = taskForm.getParameter("status");
        Status status = "".equals(statusRaw) || null == statusRaw  ? null : Status.valueOf(statusRaw.toUpperCase(Locale.ROOT));
        task.setStatus(status);

        task.setType(taskForm.getParameter("type"));
        task.setDescription(taskForm.getParameter("description"));
        task.setProductDescription(taskForm.getParameter("product-description"));

        String deadlineRaw = taskForm.getParameter("deadline");
        LocalDate deadline = "".equals(deadlineRaw) || deadlineRaw == null ? null : LocalDate.parse(deadlineRaw);
        task.setDeadline(deadline);

        String startDateRaw = taskForm.getParameter("start-date");
        LocalDate startDate = "".equals(startDateRaw) || startDateRaw == null ? null : LocalDate.parse(startDateRaw);
        task.setStartDate(startDate);

        String estTimeRaw = taskForm.getParameter("est-time");
        Integer estTime = "".equals(estTimeRaw) || estTimeRaw == null ? null : Integer.parseInt(estTimeRaw);
        task.setEstTime(estTime);


        String spentTimeRaw = taskForm.getParameter("spent-time");
        Integer spentTime = "".equals(spentTimeRaw) || spentTimeRaw == null ? null : Integer.parseInt(spentTimeRaw);
        task.setSpentTime(spentTime);

        return task;
    }


    @Autowired
    public void setRepository(TaskRepository repository){
        this.repository = repository;
    }
}
