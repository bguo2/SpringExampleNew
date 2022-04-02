package com.example.test.repository;

import com.example.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRepoService {
    private NamedParameterJdbcTemplate template;

    @Autowired
    public UserRepoService(DataSource source) {
        template = new NamedParameterJdbcTemplate(source);
    }

    public List<User> getAllUsers(long departmentId) {
        String sql = "select * from user u where u.departmentId = :departmentId";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("departmentId", departmentId);
        List<User> result = template.query(sql, parameters, new BeanPropertyRowMapper(User.class));
        return result;
    }
}
