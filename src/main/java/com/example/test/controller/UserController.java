package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.repository.UserJdbcRepoService;
import com.example.test.repository.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {
    private final UserJdbcRepoService userJdbcRepoService;
    private final UserService userService;

    @Autowired
    public UserController(UserJdbcRepoService userJdbcRepoService,
                          UserService userService) {
        this.userJdbcRepoService = userJdbcRepoService;
        this.userService = userService;
    }

    @GetMapping(value = "/users", params = "id")
    public ResponseEntity<CollectionModel<User>> getUsers(@RequestParam long id) {
        List<User> result = userJdbcRepoService.getAllUsers(id);

        //HATEOAS
        Link selfLink = linkTo(methodOn(UserController.class).getUsers(id)).withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(result, selfLink));
    }

    @PostMapping(value = "/user/update", params = "id")
    public ResponseEntity<Boolean> updateUser(@RequestParam long id) {
        Boolean result = userJdbcRepoService.insert(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/user/jpa/all")
    ResponseEntity findAll() {
        try {
            List<User> users = this.userService.findAll();
            return new ResponseEntity(users, HttpStatus.OK);
        } catch (Exception e) {
            log.error("exception: ", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/user/jpa")
    ResponseEntity findByFirstName(@RequestParam String name) {
        try {
            User user = this.userService.getUserByName(name);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            log.error("exception: ", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
