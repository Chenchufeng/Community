package com.example.blogforum.controller;

import com.example.blogforum.dto.AccessTokenDTO;
import com.example.blogforum.model.User;
import com.example.blogforum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    AccessTokenDTO accessTokenDTO;
    @Autowired
    GithubProvider githubProvider;
    @Autowired
    User user;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state" ) String state){
        //给accessTokenDTO赋值
        accessTokenDTO.setClient_id("6efa0c9edf5b747a0e654a5b895e0c3fd2cffb6fe448f000e377eac4f249b3da");
        accessTokenDTO.setClient_Secret("8ae21a5f3b9fad7388f042f6187eae2d1d4d4f2f3e6542ef7304e8cc5c758ef9");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);

        String token = githubProvider.getAccessToken(accessTokenDTO);
        user = githubProvider.getUserByToken(token);
        System.out.println("User name: "+user.getName());
        System.out.println("User email: "+user.getEmail());
        return "redirect:/";
    }
}
