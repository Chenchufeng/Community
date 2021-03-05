package com.example.blogforum.controller;

import com.example.blogforum.dto.AccessTokenDTO;
import com.example.blogforum.model.User;
import com.example.blogforum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    AccessTokenDTO accessTokenDTO;
    @Autowired
    GithubProvider githubProvider;
    @Autowired
    User user;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state" ) String state){
        //给accessTokenDTO赋值
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_Secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String token = githubProvider.getAccessToken(accessTokenDTO);
        user = githubProvider.getUserByToken(token);
        System.out.println("User name: "+user.getName());
        System.out.println("User email: "+user.getEmail());

        return "redirect:/";
    }
}
