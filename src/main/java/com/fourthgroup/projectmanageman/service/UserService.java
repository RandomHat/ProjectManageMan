package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.repository.UserRepository;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Nov 3, 2021
    ===============================
 */
public class UserService {

    UserRepository userRepository = new UserRepository();

    public boolean writeUser(User user){
        return userRepository.writeUser(user);

    }

    public boolean submitAccountDetails(WebRequest requestFromUser) {
            User currentUser = new User(
                    requestFromUser.getParameter("firstname"),
                    requestFromUser.getParameter("lastname"),
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
