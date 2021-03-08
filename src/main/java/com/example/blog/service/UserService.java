package com.example.blog.service;

import com.example.blog.entity.User;
import com.example.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chufeng Chen
 * @Description:
 * @Date:Create：in 2021/3/7 23:35
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void addUser(User user){
        userMapper.addUser(user);
    }

}
