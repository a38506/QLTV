package com.libmanagement.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.libmanagement.core.models.Student;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login (@RequestBody Student form) {

        System.out.println("User name: " + form.getUsername());
        System.out.println("Password: " + form.getPassword());
        return ResponseEntity.ok(HttpStatus.OK);


        
    //     String username = form.getUsername();
    //     String password = form.getPassword();

    //     if (check(username, password)) {
    //         System.out.println("Login successful!");
    //         return ResponseEntity.ok(HttpStatus.OK);
    //     } else {
    //         System.out.println("Login failed!");
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    //     }
    // }
    // private boolean check(String username, String password) {
    //     return "admin".equals(username) && "password123".equals(password);
    // }
}}