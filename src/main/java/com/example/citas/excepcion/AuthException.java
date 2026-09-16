package com.example.citas.excepcion;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
