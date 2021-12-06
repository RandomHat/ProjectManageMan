package com.fourthgroup.projectmanageman.controller;


import com.fourthgroup.projectmanageman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/*
    ===============================
    Author: Mark Kaplan Hansen
    Date: Nov 3, 2021
    ===============================
 */

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/create-account")
    public String createAccountView(HttpSession session){
        return "CreateAccount";
    }

    @PostMapping("/create-account")
    public String createAccountForm(WebRequest requestFromUser){

        if(userService.samePassword(requestFromUser)){
            if(userService.submitAccountDetails(requestFromUser)) {
                return "redirect:/";
            }
        }
        return "redirect:/WrongAccountInfo";
    }

}
