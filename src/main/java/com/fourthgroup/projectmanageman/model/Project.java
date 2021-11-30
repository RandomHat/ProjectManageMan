package com.fourthgroup.projectmanageman.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Nov 26, 2021
    ===============================
 */

public class Project {
    private int id;
    private int parentProjectID;
    private String title;
    private String description;
    private String client;
    private LocalDate startDate; //Java: YYYY-MM-DDTHH:MM:SS - SQL format: YYYY-MM-DD
    private LocalDate deadline; //Java: 2021-11-28T10:15:30 - SQL format: YYYY-MM-DD
    private int estTimeHours;
    private int spentTimeHours;
    private Status status;

    public Project(int parentProjectID,
                   String title,
                   String description,
                   String client,
                   LocalDate startDate,
                   LocalDate deadline,
                   int estTimeHours,
                   int spentTimeHours,
                   Status status) {
        this.parentProjectID = parentProjectID;
        this.title = title;
        this.description = description;
        this.client = client;
        this.startDate = startDate;
        this.deadline = deadline;
        this.estTimeHours = estTimeHours;
        this.spentTimeHours = spentTimeHours;
        this.status = status;
    }

    public Project() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", parentProjectID=" + parentProjectID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", client='" + client + '\'' +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                ", estTimeHours=" + estTimeHours +
                ", spentTimeHours=" + spentTimeHours +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getParentProjectID() {
        return parentProjectID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getClient() {
        return client;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getEstTimeHours() {
        return estTimeHours;
    }

    public int getSpentTimeHours() {
        return spentTimeHours;
    }

    public Status getStatus() {
        return status;
    }

    public void setParentProjectID(int parentProjectID) {
        this.parentProjectID = parentProjectID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setEstTimeHours(int estTimeHours) {
        this.estTimeHours = estTimeHours;
    }

    public void setSpentTimeHours(int spentTimeHours) {
        this.spentTimeHours = spentTimeHours;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

