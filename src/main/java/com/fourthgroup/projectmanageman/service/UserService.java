package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.User;

public class UserService {

    public User makeUser(String firstName, String lastName, String phonenumber, String email ,String username ,String password) {
        User user = new User(firstName,lastName,phonenumber,email ,username ,password);
        return user;
    }
}
