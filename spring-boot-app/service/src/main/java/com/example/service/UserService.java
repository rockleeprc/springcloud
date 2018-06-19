package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Integer save(User user) {
        return userDao.save(user);
    }

    public boolean update(User user) {
        return userDao.update(user);
    }

    public List<User> getUserByDeparmentId(Integer deparmentId) {
        return userDao.getUserByDepartmentId(deparmentId);
    }

    public User getUser(Integer id) {
        return userDao.getUser(id);
    }

    public Integer getDepartmentCount(Integer deparmentId) {
        return userDao.getDepartmentCount(deparmentId);
    }
}
