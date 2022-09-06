package com.example.test.repository;

import com.example.test.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface UserService {
    User getUserByName(String name);
    List<User> findAll();
}
