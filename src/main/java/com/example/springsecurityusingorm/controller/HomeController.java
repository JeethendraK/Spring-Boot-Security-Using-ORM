package com.example.springsecurityusingorm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String showHome() {
        return "home";
    }

    //Only After Login
    @GetMapping("/hello")
    public String showHello() {
        return "hello";
    }
    //only Admin after login
    @GetMapping("/admin")
    public String showAdmin() {
        return "admin";
    }

    //only Customer after login
    @GetMapping("/customer")
    public String showCustomer() {
        return "customer";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

}
