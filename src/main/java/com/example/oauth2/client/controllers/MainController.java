package com.example.oauth2.client.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(OAuth2AuthenticationToken token) {
        System.out.println(token.getPrincipal());
        return "main.html";
    }

    @GetMapping("/home")
    public void home(){
        System.out.println("aaa");
    }

    @PostMapping("/home")
    public void home1(){
        System.out.println("bbb");
    }

}
