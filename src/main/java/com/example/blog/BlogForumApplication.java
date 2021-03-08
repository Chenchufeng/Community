package com.example.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MapperScan("com.example.blog.mapper") //扫描的mapper
@SpringBootApplication
public class BlogForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogForumApplication.class, args);
    }

}
