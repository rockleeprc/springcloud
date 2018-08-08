package com.com.example;

import com.example.SpringBootAppApplication;
import com.example.config.PropertyConfig;
import com.example.service.UserService;
import com.example.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试service
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootAppApplication.class)
public class ApplicationTest2 {

    @Autowired
    private UserService userService;
    @Autowired
    private PropertyConfig config;


    @Test
    public void property() {
        System.out.println(config);
    }

    @Test
    public void testInfo() throws Exception {
        System.out.println(userService);

    }


    @Test
    public void contextLoads() throws Exception {
    }

}