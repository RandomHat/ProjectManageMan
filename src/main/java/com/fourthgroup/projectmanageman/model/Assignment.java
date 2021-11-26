package com.fourthgroup.projectmanageman.model;

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
