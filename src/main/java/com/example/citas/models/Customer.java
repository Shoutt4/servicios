package com.example.citas.models;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_customer", uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email_adres"))
public class Customer {

    @Id
    @UuidGenerator
    private String idCustomer;
    private String firsName;
    private String lastName;
    @Column(name = "email_adres", nullable = false)
    private String email;
    @Embedded
    private Adres adres;
}
