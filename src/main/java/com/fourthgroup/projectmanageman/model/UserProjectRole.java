package com.fourthgroup.projectmanageman.model;

/*
    ===============================
    Author: Frederik Wandall von Benzon
    Date: Nov 26, 2021
    ===============================
 */


public class UserProjectRole {
    int id;
    int roleId;
    int personId;
    int projectId;

    public UserProjectRole(int id, int roleId, int personId, int projectId) {
        this.id = id;
        this.roleId = roleId;
        this.personId = personId;
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "UserProjectRole{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", personId=" + personId +
                ", projectId=" + projectId +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getProjectId() {
        return projectId;
    }

    /*
    public void setId(int id) {
        this.id = id;
    }
    */

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
