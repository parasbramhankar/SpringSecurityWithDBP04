package com.example.SpringSecurityWithDBP04.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getBalance")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String getBalance(){
        return "you balance : 10000";
    }

    @GetMapping("/aboutUs")
    @PreAuthorize("hasRole('USER')")
    public String aboutUs(){
        return "this is about us page";
    }

    @GetMapping("/contactUs")
    @PreAuthorize("hasRole('USER')")
    public String contactUs(){
        return "this is the contact us";
    }

    @GetMapping("/transactions")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String transactions(){
        return "here is your transactions history";
    }

}
