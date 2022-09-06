package com.example.test.repository;

import com.example.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserServiceJpaImpl implements UserService {

    private UserJpaRepository userJpaRepository;

    @Autowired
    public UserServiceJpaImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User getUserByName(String name) {
        return this.userJpaRepository.findByName(name);
    }

    @Override
    public List<User> findAll() {
        return this.userJpaRepository.findAll();
    }
}
