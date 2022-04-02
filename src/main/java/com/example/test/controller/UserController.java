package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.repository.UserJpaRepository;
import com.example.test.repository.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserRepoService userRepoService;
    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserController(UserRepoService userRepoService,
                          UserJpaRepository userJpaRepository) {
        this.userRepoService = userRepoService;
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<CollectionModel<User>> getUsers(@RequestParam long id) {
        List<User> result = userRepoService.getAllUsers(id);

        //HATEOAS
        Link selfLink = linkTo(methodOn(UserController.class).getUsers(id)).withSelfRel();

        //List<User> jpaResult = userJpaRepository.findAll();
        return ResponseEntity.ok(CollectionModel.of(result, selfLink));
    }
}
