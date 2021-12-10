package com.fourthgroup.projectmanageman.model;

public class AssignedProjectUsers {
    private int projectId;
    private int userId;
    private String firstName;
    private String lastName;
    private String roleName;

    public AssignedProjectUsers() {}

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRoleName() {
        return roleName;
    }
}
