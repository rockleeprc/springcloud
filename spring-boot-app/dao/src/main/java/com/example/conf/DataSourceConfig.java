package com.example.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configurable
public class DataSourceConfig {
    @Bean(name = "dataSource")
    public DataSource dataSource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;
    }

}
