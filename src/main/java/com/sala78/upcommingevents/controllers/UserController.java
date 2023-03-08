package com.sala78.upcommingevents.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sala78.upcommingevents.models.User;
import com.sala78.upcommingevents.services.UserService;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Map<String, String>> save(@RequestBody User user) {

        try {
            User userDB = service.store(user);
            Map<String, String> json = new HashMap<>();

            json.put("username", userDB.getUsername());
            json.put("message", "successful sign up");
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        } catch (Exception e) {
            Map<String, String> json = new HashMap<>();

            json.put("prolem", e.getMessage());
            json.put("message", "Error to sign up");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }
    }

    @GetMapping("")
    public List<User> listAll() {
        return service.listAll();
    }
}
