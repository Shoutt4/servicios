package com.example.citas.dto;

public class UpdateUserDTO {

    private String nombre;
    private String correo;
    private int phone;

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPhone() {
        return phone;
    }

    public UpdateUserDTO(String nombre, String correo, int phone) {
        this.nombre = nombre;
        this.correo = correo;
        this.phone = phone;
    }

}
