package com.example.blogforum.dto;

import org.springframework.stereotype.Component;

@Component
public class AccessTokenDTO {
    private Integer id;
    private String client_id;
    private String client_Secret;
    private String redirect_uri;
    private String code;
    private String state;

    public String getClient_Secret() {
        return client_Secret;
    }

    public void setClient_Secret(String client_Secret) {
        this.client_Secret = client_Secret;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
