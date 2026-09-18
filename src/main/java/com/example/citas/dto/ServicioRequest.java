package com.example.citas.dto;

import java.sql.Time;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ServicioRequest {
    @NotBlank(message = "el nombre es obligatorio")
    @Size(min = 4, max = 16, message = "el nombre debe ser entre 4 y 16 caracteres")
    private String nombre;
    @Size(min = 4, max = 150, message = "el texto debe ser entre 4 a 150 caracteres")
    private String descipcion;
    @NotBlank(message = "la duracion del servicio es obligatorio")
    private Time duracion_servicio;
    @NotBlank(message = "el estado es obligatiorio")
    private Boolean activo;
    @PositiveOrZero(message = "el precio debe ser positivo o 0")
    @NotBlank(message = "el precio es obligatorio")
    private double precio;

    public ServicioRequest(String nombre, String descripcion, Time duracion_servicio, Boolean activo, double precio) {
        this.nombre = nombre;
        this.descipcion = descripcion;
        this.duracion_servicio = duracion_servicio;
        this.activo = activo;
        this.precio = precio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Time getDuracion_servicio() {
        return duracion_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescipcion() {
        return descipcion;
    }
}
