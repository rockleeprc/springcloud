package com.com.example;

import com.example.SpringBootAppApplication;
import com.example.service.UserService;
import com.example.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 直接调用controller
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootAppApplication.class)
public class ApplicationTest4 {

    @Autowired
  private UserController userController;


    @Test
    public void testInfo() throws Exception {
        System.out.println(userController.info());
    }
    @Test
    public void testGetUser() throws Exception {
        System.out.println(userController.getUser(1));
    }


    @Test
    public void contextLoads() throws Exception {
    }

}