package com.com.example;

import com.example.SpringBootAppApplication;
import com.example.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 使用MockMvc
 * @SpringBootTest 不能和 @WebMvcTest 同时使用 * 如果使用MockMvc对象的话，需要另外加上@AutoConfigureMockMvc注解
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootAppApplication.class)
@AutoConfigureMockMvc
public class ApplicationTest3 {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testInfo() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/info"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/user/info"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("hello"));
    }


    @Test
    public void contextLoads() throws Exception {
    }

}