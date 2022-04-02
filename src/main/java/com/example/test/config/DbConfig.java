package com.example.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.testdb")
    public DataSource testDbDatasource()
    {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }
}
