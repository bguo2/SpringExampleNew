package com.example.test.repository;

import com.example.test.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserJdbcRepoService {
    private int count = 0;
    private NamedParameterJdbcTemplate template;
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    public UserJdbcRepoService(DataSource source) {
        DataSourceTransactionManager  transactionManager = new DataSourceTransactionManager(source);
        template = new NamedParameterJdbcTemplate(transactionManager.getDataSource());
        platformTransactionManager = transactionManager;
    }

    public List<User> getAllUsers(long departmentId) {
        try {
            String sql = "select * from user u where u.departmentId = :departmentId";
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("departmentId", departmentId);
            List<User> result = template.query(sql, parameters, new BeanPropertyRowMapper(User.class));
            return result;
        }
        catch (Exception e) {
            log.error("getAllUsers exception: e", e);
        }
        return new ArrayList<>();
    }

    public boolean insert(long departmentId) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            count++;
            String sql = "insert into user(FirstName, LastName,DepartmentId) values(:firstName, :lastName, :departmentId);";
            Map<String, Object> map = new HashMap<>();
            map.put("firstName", "test_"+count+departmentId);
            map.put("lastName", "test_last_"+count+departmentId);
            //let it throw exception
            departmentId = 0;
            map.put("departmentId", departmentId);
            template.update(sql, map);
            platformTransactionManager.commit(transactionStatus);
            return true;
        } catch (Exception e) {
            log.error("insert exception:  ", e);
            platformTransactionManager.rollback(transactionStatus);
        }
        return false;
    }
}
