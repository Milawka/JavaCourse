package project.spring.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.spring.model.entities.User;
import project.spring.model.service.UserService;
import project.spring.model.service.UserServiceImpl;

@Log
@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET )
    public String signUp(){
         return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String createNewUser(@RequestParam String login, @RequestParam String password, Model model){
        if (login == null || password == null) {
            log.info("Null login and/or password");
            return "signUp";
        }

        if (userService.findUserByLogin(login)!= null){
            model.addAttribute("message","This login already exists in system!");
            log.info("User already exists");
            return "signUp";
        }

        User user = userService.createNewUser(login, password);

        if (user!=null){
            log.info("New user succesfully created" + user.getLogin());
            return "redirect:login";
        }
        log.info("User is null");
        model.addAttribute("message","Problems with creating new user");
        return "signUp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }
}