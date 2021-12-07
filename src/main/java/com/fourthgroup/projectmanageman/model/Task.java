package com.fourthgroup.projectmanageman.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Nov 26, 2021
    ===============================
 */

public class Task {

    private final int id;
    private int projectID;
    private int parentTaskID;
    private String title;
    private String type;
    private String description;
    private String productDescription;
    private Set<Assignment> assignedUsers;
    private int estTime;
    private int spentTime;
    private LocalDate startDate;
    private LocalDate deadline;
    Status status;

    public Task(int id, int pID, String title, String type,
                String desc, String pdesc, Set<Assignment> assignments,
                int estTime, int spentTime, LocalDate startDate,
                LocalDate deadline, Status status) {
        this.id = id;
        this.parentTaskID = pID;
        this.title = title;
        this.type = type;
        this.description = desc;
        this.productDescription = pdesc;
        this.assignedUsers = assignments;
        this.estTime = estTime;
        this.spentTime = spentTime;
        this.startDate = startDate;
        this.deadline = deadline;
        this.status = status;
    }
    public Task(int id){
        this.id = id;
    }

    public Task(int taskID, int projectID, int parentTaskID, String title, String description, String productDescription, int estTime, int spentTime, LocalDate startDate, LocalDate deadline, Status status) {
        this.id = taskID;
        this.projectID = projectID;
        this.parentTaskID = parentTaskID;
        this.title = title;
        this.description = description;
        this.productDescription = productDescription;
        this.estTime = estTime;
        this.spentTime = spentTime;
        this.startDate = startDate;
        this.deadline = deadline;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getParentTaskID() {
        return parentTaskID;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Set<Assignment> getAssignedUsers() {
        return assignedUsers;
    }

    public int getEstTime() {
        return estTime;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", parentTaskID=" + parentTaskID +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", assignedUsers=" + assignedUsers +
                ", estTime=" + estTime +
                ", spentTime=" + spentTime +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                ", status=" + status +
                '}';
    }
}
