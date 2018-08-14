package com.yuneke.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
//        initParameters.put("allow", "10.8.9.115");  // IP白名单 (没有配置或者为空，则允许所有访问)
//        initParameters.put("deny", ""); // IP黑名单 (存在共同时，deny优先于allow)
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "geek");
        initParameters.put("resetEnable", "false"); // 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean druidWebStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}