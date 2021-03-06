package com.fourthgroup.projectmanageman.model;

import java.time.LocalDate;
import java.sql.Date;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Nov 26, 2021
    ===============================
 */

public class Task {

    private Integer taskID;
    private Integer projectID;
    private Integer parentTaskID;
    private String title;
    private String type;
    private String description;
    private String productDescription;
    private Integer estTime;
    private Integer spentTime;
    private LocalDate startDate;
    private LocalDate deadline;
    Status status;

    public Task(int taskID, int projectID, int parentTaskID, String title, String type,
                String desc, String pdesc, int estTime,
                int spentTime, LocalDate startDate,
                LocalDate deadline, Status status) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.parentTaskID = parentTaskID;
        this.title = title;
        this.type = type;
        this.description = desc;
        this.productDescription = pdesc;
        this.estTime = estTime;
        this.spentTime = spentTime;
        this.startDate = startDate;
        this.deadline = deadline;
        this.status = status;
    }
    public Task(int taskID){
        this.taskID = taskID;
    }

    public Task(){};

    public Integer getTaskID() {
        return taskID;
    }

    public Integer getProjectID(){
        return projectID;
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

    public Integer getEstTime() {
        return estTime;
    }

    public Integer getSpentTime() {
        return spentTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Date getStartDateSQL(){
        if (this.startDate != null){
            return Date.valueOf(String.valueOf(this.startDate));
        }
        return null;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Date getDeadlineSQL(){
        if (this.deadline != null){
            return Date.valueOf(String.valueOf(this.deadline));
        }
        return null;
    }

    public Status getStatus() {
        return status;
    }

    public void setProjectID(Integer projectID){
        this.projectID = projectID;
    }

    public void setParentTaskID(Integer parentTaskID) {
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

    public void setEstTime(Integer estTime) {
        this.estTime = estTime;
    }

    public void setSpentTime(Integer spentTime) {
        this.spentTime = spentTime;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + taskID +
                ", parentTaskID=" + parentTaskID +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", estTime=" + estTime +
                ", spentTime=" + spentTime +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                ", status=" + status +
                '}';
    }
}
