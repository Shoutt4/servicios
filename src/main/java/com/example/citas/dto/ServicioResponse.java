package com.example.citas.dto;

import java.sql.Time;

public class ServicioResponse {
    private String id_servicio;
    private String nombre;
    private Time duracion_servicio;
    private Boolean activo;
    private double precio;

    public ServicioResponse(
            String id_servicio,
            String nombre,
            Time duracion_servicio,
            Boolean activo,
            double precio) {
        this.id_servicio = id_servicio;
        this.nombre = nombre;
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

    public String getId_servicio() {
        return id_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

}
