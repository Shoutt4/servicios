package com.example.citas.models;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_manager")
public class Manager {
    @Id
    @UuidGenerator
    private String managerId;
    private String firsName;
    private String lastName;
}
