package com.example.test.repository;

import com.example.test.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends CrudRepository<User, Long> {
    @Query(value="SELECT * FROM User a", nativeQuery = true)
    List<User> findAll();

    @Query(value="SELECT a.* FROM User a WHERE a.firstName = :name LIMIT 1", nativeQuery = true)
    User findByName(@Param("name") String name);
}
