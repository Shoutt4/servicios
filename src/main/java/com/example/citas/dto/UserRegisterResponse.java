package com.example.citas.dto;

public class UserRegisterResponse {
    private String id_usuario;
    private String name;
    private String email;
    private int phone;

    public UserRegisterResponse(String id_usuario, String name, String email, int phone) {
        this.id_usuario = id_usuario;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

}
