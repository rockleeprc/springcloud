package com.exam.service;

import com.exam.mapper.UserMapper;
import com.exam.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@CacheConfig(cacheNames = "user")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer insert(User user) {
        int result = userMapper.insert(user);
        int i = 1/0;
        return result;
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    @Cacheable(key="#id")
    public User getUser(Integer id) {
        return userMapper.selectByID(id);
    }

}
