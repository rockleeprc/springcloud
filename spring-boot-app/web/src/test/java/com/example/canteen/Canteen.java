package com.example.canteen;

import com.example.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Canteen {
    @Autowired
    private UserDao userDao;

    @Test
    public void t() {
        System.out.println(userDao.getUser(1));
    }
}
