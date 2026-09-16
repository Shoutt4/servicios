package com.example.citas.models;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Role {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id_Role;
    private String name;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Usuario> users;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public String getId() {
        return id_Role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
