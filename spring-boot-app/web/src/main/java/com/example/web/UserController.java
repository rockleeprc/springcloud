package com.example.web;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String info() {
        return "hello";
    }

    @GetMapping("/save")
    public int save(User user) {
        return userService.save(user);
    }

    @GetMapping("/departmentCount")
    public int getDepartmentCount(int departmentId) {
        return userService.getDepartmentCount(departmentId);
    }

    @GetMapping("/getUser")
    public User getUser(Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/update")
    public boolean update(User user) {
        return userService.update(user);
    }

    @GetMapping("/getUserByDepartmentId")
    public List<User> getUserByDepartmentId(int departmentId){
        return userService.getUserByDeparmentId(departmentId);
    }
}
