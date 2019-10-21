package com.foo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
public class TxConfig {
    @Bean
    public DataSource getDataSource() {
        return null;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

/*
1、配置Datasource
2、配置DataSourceTransactionManager
3、开启事物管理功能@EnableTransactionManagement
4、使用@Transactional注解
 */
