package com.example.citas.dto;

public class RoleResponse {

    private String idRol;
    private String nombre;

    public RoleResponse(String idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public String getIdRol() {
        return idRol;
    }

    public String getNombre() {
        return nombre;
    }
}
