package com.example.citas.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tlb_user")
public class Usuario {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id_user;
    private String name;
    private String email;
    private String password;
    private int phone;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    private LocalDateTime createAt;

    public Usuario() {

    }

    public Usuario(String a, String b, String c, int d) {
        this.name = a;
        this.email = b;
        this.password = c;
        this.phone = d;
    }

    public Usuario(String a, String b, String c, int d, Role rol) {
        this.name = a;
        this.email = b;
        this.password = c;
        this.phone = d;
        this.role = rol;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public String getEmail() {
        return email;
    }

    public String getId_user() {
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

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
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

}
