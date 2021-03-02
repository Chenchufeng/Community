package com.example.blogforum.provider;

import com.example.blogforum.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String str = response.body().string();
            System.out.println("response: "+str);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
