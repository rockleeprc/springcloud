package com.exam.controller;

import com.exam.pojo.User;
import com.exam.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    Log log  = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String info() {
        log.info("log....");
        return "hello Boot AAA";
    }

    @GetMapping("/save")
    public int save(User user) {
        return userService.insert(user);
    }

    @GetMapping("/exception")
    public void exception(){
        throw new RuntimeException("exception");
    }


    @GetMapping("/getUser")
    public User getUser(Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/update")
    public int update(User user) {
        return userService.update(user);
    }

}
