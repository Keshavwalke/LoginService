package com.example.logindemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AuthController {

    @GetMapping("/user")
    public void login (){
    }

    @GetMapping("/users")
    public Boolean validate(){
        return null;
    }
}
