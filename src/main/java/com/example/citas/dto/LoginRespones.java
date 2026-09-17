package com.example.citas.dto;

public class LoginRespones {
    private String token;

    public LoginRespones(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
