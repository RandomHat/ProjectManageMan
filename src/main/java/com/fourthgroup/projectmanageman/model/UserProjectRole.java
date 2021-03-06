package com.fourthgroup.projectmanageman.model;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Nov 26, 2021
    ===============================
 */


public class UserProjectRole {
    private int id;
    private int roleId;
    private int userId;
    private int projectId;

    public UserProjectRole(int roleId, int personId, int projectId) {
        this.roleId = roleId;
        this.userId = personId;
        this.projectId = projectId;
    }

    public UserProjectRole() {
    }

    @Override
    public String toString() {
        return "UserProjectRole{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", personId=" + userId +
                ", projectId=" + projectId +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
