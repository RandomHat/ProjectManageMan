package com.fourthgroup.projectmanageman.model;
/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Nov 26, 2021
    ===============================
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String phonenumber;
    private String email;
    private String username;
    private String password;

    private boolean isAdmin;

    public User() {
    }

    public User(int id, String firstName, String lastName, String phonenumber, String email, String username, String password, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phonenumber = phonenumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String firstName, String lastName, String phonenumber, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phonenumber = phonenumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setAdminDB(int tinyint){
        isAdmin = tinyint == 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
