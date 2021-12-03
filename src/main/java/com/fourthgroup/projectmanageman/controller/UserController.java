package controller;

import com.fourthgroup.projectmanageman.model.User;
import com.fourthgroup.projectmanageman.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class UserController {

    UserService userService = new UserService();

    @GetMapping("/create-account")
    public String createAccountView(HttpSession session){
        return "CreateAccount";
    }

    @PostMapping("/create-account")
    public String createAccountForm(WebRequest requestFromUser){

        if(Objects.equals(requestFromUser.getParameter("password"), requestFromUser.getParameter("confirmPassword"))){
            if(userService.submitAccountDetails(requestFromUser)) {
                return "redirect:/";
            }
        }
        return "redirect:/WrongAccountInfo";
    }
}
