package com.example.blog.mapper;

import com.example.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: chufeng Chen
 * @Description:
 * @Date:Create：in 2021/3/6 14:52
 */

@Mapper
public interface UserMapper {
    void addUser(User user);
}