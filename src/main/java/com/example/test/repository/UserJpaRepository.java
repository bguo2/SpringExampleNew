package com.example.test.repository;

import com.example.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//not working
public interface UserJpaRepository extends JpaRepository<User, Long> {
/*
    @Query(value="select u.* from user u where u.departmentId = ?1", nativeQuery = true)
    List<User> findByDepartmentId(Long departmentId);

    @Query(value="select * from user", nativeQuery = true)
    List<User> findAllUsers();

 */
}
