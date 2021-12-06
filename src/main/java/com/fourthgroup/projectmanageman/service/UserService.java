package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Nov 3, 2021
    ===============================
 */
@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean writeUser(User user){
        return userRepository.writeUser(user);

    }

    public boolean submitAccountDetails(WebRequest requestFromUser) {
            User currentUser = new User(
                    requestFromUser.getParameter("firstName"),
                    requestFromUser.getParameter("lastName"),
                    requestFromUser.getParameter("phonenumber"),
                    requestFromUser.getParameter("email"),
                    requestFromUser.getParameter("username"),
                    requestFromUser.getParameter("password"));

            return writeUser(currentUser);
    }

    public boolean samePassword(WebRequest requestFromUser){
        return Objects.equals(requestFromUser.getParameter("password"), requestFromUser.getParameter("confirmPassword"));
    }


}
