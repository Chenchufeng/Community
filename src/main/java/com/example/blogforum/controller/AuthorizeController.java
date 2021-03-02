package com.example.blogforum.controller;

import com.example.blogforum.dto.AccessTokenDTO;
import com.example.blogforum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;

    @GetMapping("/login")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state" ) String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        //给accessTokenDTO赋值
        accessTokenDTO.setClient_id("9ea948294b8d34459f49");
        accessTokenDTO.setClient_secret("878da2a686f8adb7e7b37755da7795b4f651596d");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/login");
        accessTokenDTO.setState(state);
        githubProvider.getAccessToken(accessTokenDTO);
        return "hello";
    }
}
