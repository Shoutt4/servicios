package com.example.citas.models;

import java.security.Provider.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Reserva {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id_reserva;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario_id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Servicio servicio_id;
    private LocalDateTime fecha_reserva;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private LocalDateTime create_at;

    public Reserva() {

    }

    public Reserva(Usuario usuario_id, Servicio servicio_id, Estado estado) {
        this.usuario_id = usuario_id;
        this.servicio_id = servicio_id;
        this.fecha_reserva = fecha_reserva;
        this.estado = estado;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public Estado getEstado() {
        return estado;
    }

    public LocalDateTime getFecha_reserva() {
        return fecha_reserva;
    }

    public String getId_reserva() {
        return id_reserva;
    }

    public Servicio getServicio_id() {
        return servicio_id;
    }

    public Usuario getUsuario_id() {
        return usuario_id;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setFecha_reserva(LocalDateTime fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public void setServicio_id(Servicio servicio_id) {
        this.servicio_id = servicio_id;
    }

    public void setUsuario_id(Usuario usuario_id) {
        this.usuario_id = usuario_id;
    }

    @PrePersist
    public void onCreate() {
        this.create_at = LocalDateTime.now();
        this.fecha_reserva = LocalDateTime.now();
    }
}
