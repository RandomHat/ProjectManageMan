package com.fourthgroup.projectmanageman.model;

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
    private int parentTaskID;
    private String title;
    private String type;
    private String description;
    private String productDescription;
    private Set<Assignment> assignedUsers;
    private int estTime;
    private int spentTime;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
    Status status;

    public Task(int id, int pID, String title, String type,
                String desc, String pdesc, Set<Assignment> assignments,
                int estTime, int spentTime, LocalDateTime startDate,
                LocalDateTime deadline, Status status) {
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

    public Task(){};

    public Integer getId() {
        return id;
    }

    public Integer getParentTaskID() {
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

    public Integer getEstTime() {
        return estTime;
    }

    public Integer getSpentTime() {
        return spentTime;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setParentTaskID(int parentTaskID) {
        this.parentTaskID = parentTaskID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setAssignedUsers(Set<Assignment> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public void setEstTime(int estTime) {
        this.estTime = estTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setStatus(Status status) {
        this.status = status;
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
