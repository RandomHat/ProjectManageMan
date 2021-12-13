package com.fourthgroup.projectmanageman.service;

import com.fourthgroup.projectmanageman.model.Project;
import com.fourthgroup.projectmanageman.model.Task;
import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/*
    ===============================
    Author: Mark Kaplan Hansen
    github: BenAtic-KEA
    Date: Dec 12, 2021
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


    public User loginApproved(WebRequest requestFromUser) {
        String username = requestFromUser.getParameter("username");
        String password = requestFromUser.getParameter("password");
        User user = userRepository.userLogin(username,password);
        if(Objects.equals(username, user.getUsername()) && Objects.equals(password, user.getPassword())){
            return user;
        } else {
            user.setId(-1);
            return user;
        }
    }

    public List<Project> userProjectList(HttpSession session) {
       User user = (User) session.getAttribute("user");
       return userRepository.userProjectList(user.getId());
    }

    public List<Task> userTaskList(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userRepository.userTaskList(user.getId());
    }
}
