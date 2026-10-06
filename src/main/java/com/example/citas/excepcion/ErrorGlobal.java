package com.example.citas.excepcion;

import lombok.Data;

@Data
public class ErrorGlobal extends RuntimeException {
    private String status;

    public ErrorGlobal(String status, String message) {
        super(message);
        this.status = status;
    }
}
