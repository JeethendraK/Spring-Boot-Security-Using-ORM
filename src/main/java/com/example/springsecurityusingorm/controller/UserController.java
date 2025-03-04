package com.example.springsecurityusingorm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springsecurityusingorm.entity.User;
import com.example.springsecurityusingorm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    //1.Show registration page
    @GetMapping("/register")
    public String showreg(){
        return "UserRegistration";
    }

    //2.save user data
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, Model model){
        Integer id = service.saveUser(user);
        String message = "User '"+id+"' saved";
        model.addAttribute("message", message);
        return "UserRegistration";
    }
}
