package com.example.citas.excepcion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoriaExcetoon.class)
    public ResponseEntity<Map<String, String>> exceptionCategoria(CategoriaExcetoon ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> ilegalArgumentEx(IllegalArgumentException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(ServicioException.class)
    public ResponseEntity<Map<String, String>> errorService(ServicioException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> res = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> res.put(err.getField(),
                        err.getDefaultMessage()));
        Map<String, Object> resspuesta = new HashMap<>();
        resspuesta.put("message", "errores de campo");
        resspuesta.put("error", res);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resspuesta);

    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Map<String, String>> exceptionAuth(AuthException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("status", "403", "message", exception.getMessage()));
    }

    @ExceptionHandler(ErrorGlobal.class)
    public ResponseEntity<Map<String, Object>> excpetionGlobla(ErrorGlobal ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("sucess", "false");
        response.put("status", ex.getStatus());
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
