package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/home")
@RequestMapping("/public")
public class HomeController {

    //Home for Testing
    @GetMapping("/home")
    public String home(){
        return "This is home page";
    }

    //Login for Testing
    @GetMapping("/login")
    public String login(){
        return "This is login page";
    }

    //Register for Testing
    @GetMapping("/register")
    public String register(){
        return "This is register page";
    }
}
