package com.fourthgroup.projectmanageman.model;

/*
    ===============================
    Author: Simon Roswall Jørgensen
    Date: Nov 26, 2021
    ===============================
 */

public class Assignment {

    private final int id;
    private final UserProjectRole role;
    private final int taskID;

    public Assignment(int id, UserProjectRole role, int taskID){
        this.id = id;
        this.role = role;
        this.taskID = taskID;
    }

    public int getId() {
        return id;
    }

    public UserProjectRole getRole() {
        return role;
    }

    public int getTaskID() {
        return taskID;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", role=" + role +
                ", taskID=" + taskID +
                '}';
    }
}
