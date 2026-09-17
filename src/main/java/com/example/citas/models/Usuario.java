package com.example.citas.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Usuario {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id_user;
    private String name;
    private String email;
    private String password;
    private int phone;
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
    private LocalDateTime createAt;

    public Usuario() {

    }

    public Usuario(String name, String email, String password, int phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Usuario(String name, String email, String password, int phone, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id_user;
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

    public Role getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @PrePersist
    private void onCreate() {
        this.createAt = LocalDateTime.now();
    }

}
