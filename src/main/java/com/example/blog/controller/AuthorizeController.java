package com.example.blog.controller;

import com.example.blog.dto.AccessTokenDTO;
import com.example.blog.dto.GithubUser;
import com.example.blog.entity.User;
import com.example.blog.provider.GithubProvider;
import com.example.blog.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@Data
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
    GithubUser githubUser;
    @Autowired
    UserService userService;

    @GetMapping("/callback")
    public String callback(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state" ) String state,
            HttpServletResponse response){
        //给accessTokenDTO赋值
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_Secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        githubUser = githubProvider.getUserByToken(token);
        if(githubUser != null){
            User user = new User();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());

            userService.addUser(user);
            response.addCookie(new Cookie("token",token));
            //登录成功,写cookie和session
            return "redirect:/";
        }else {
            //登录失败，重新登录
            return "redirect:/";
        }

    }
}
