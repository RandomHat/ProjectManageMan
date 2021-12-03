package controller;

import com.fourthgroup.projectmanageman.model.User;
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
        User currentUser = userService.makeUser(
                requestFromUser.getParameter("firstname"),
                requestFromUser.getParameter("lastname"),
                requestFromUser.getParameter("phonenumber"),
                requestFromUser.getParameter("email"),
                requestFromUser.getParameter("username"),
                requestFromUser.getParameter("password"));
            return "redirect:/Create_account";
        } else {
            return "redirect:/Create_account";
        }
    }
}
