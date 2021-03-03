package com.example.blogforum.provider;

import com.alibaba.fastjson.JSON;
import com.example.blogforum.dto.AccessTokenDTO;
import com.example.blogforum.model.User;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    //携带code获取token
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code&code="+accessTokenDTO.getCode()+"&client_id="+accessTokenDTO.getClient_id()+"&redirect_uri="+accessTokenDTO.getRedirect_uri()+"&client_secret="+accessTokenDTO.getClient_Secret())
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            System.out.println("response: "+str);
            String access_token = str.split(":")[1];
            String token = access_token.split("\"")[1];
            System.out.println("access_token: "+token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据token获取用户信息
    public User getUserByToken(String Token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token="+Token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            User user = JSON.parseObject(str, User.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
