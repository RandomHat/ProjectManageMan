package com.fourthgroup.projectmanageman.model;

import java.time.LocalDateTime;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Nov 26, 2021
    ===============================
 */

public class Project {
    private final int id;
    private int parentProjectID;
    private String title;
    private String client;
    private LocalDateTime startDate; //Java: YYYY-MM-DDTHH:MM:SS - SQL format: YYYY-MM-DD
    private LocalDateTime deadline; //Java: 2021-11-28T10:15:30 - SQL format: YYYY-MM-DD
    private int estTimeHours;
    private int spentTimeHours;
    private Status status;

    public Project(int id, int parentProjectID,
                   String title,
                   String client,
                   LocalDateTime startDate,
                   LocalDateTime deadline,
                   int estTimeHours,
                   int spentTimeHours,
                   Status status) {
        this.id = id;
        this.parentProjectID = parentProjectID;
        this.title = title;
        this.client = client;
        this.startDate = startDate;
        this.deadline = deadline;
        this.estTimeHours = estTimeHours;
        this.spentTimeHours = spentTimeHours;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", parentProjectID=" + parentProjectID +
                ", title='" + title + '\'' +
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public String getClient() {
        return client;
    }

    public LocalDateTime getDeadline() {
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

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setDeadline(LocalDateTime deadline) {
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

