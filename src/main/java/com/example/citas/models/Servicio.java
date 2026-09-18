package com.example.citas.models;

import java.sql.Time;
import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Servicio {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id_service;
    private String nombre;
    private String descripcion;
    private Time duracion_Service;
    private Boolean activo;
    private double precio;
    private LocalDateTime createAt;

    public Servicio() {

    }

    public Servicio(String nombre, String descripcion, Time duracion_service, Double precio, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion_Service = duracion_service;
        this.precio = precio;
        this.activo = activo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Time getDuracion_Service() {
        return duracion_Service;
    }

    public String getId_service() {
        return id_service;
    }

    public String getNombre() {
        return nombre;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion_Service(Time duracion_Service) {
        this.duracion_Service = duracion_Service;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @PrePersist
    private void onCreate() {
        this.createAt = LocalDateTime.now();
    }
    
}
