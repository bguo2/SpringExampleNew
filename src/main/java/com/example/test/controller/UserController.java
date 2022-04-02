package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.dao.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private UserDaoService userDaoService;

    @Autowired
    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam long id) {
        List<User> result = userDaoService.getAllUsers(id);
        return ResponseEntity.ok(result);
    }
}
