package com.example.citas.dto;

public class UserRequest {
    private String name;
    private String email;
    private String password;
    private int phone;

    public UserRequest(String name, String email, String password, int phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }
}
