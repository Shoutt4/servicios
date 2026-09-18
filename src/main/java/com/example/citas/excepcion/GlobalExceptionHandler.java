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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> exceptionValidateParams(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(e -> error.put(
                        e.getField(),
                        e.getDefaultMessage()));
        Map<String, Object> responseError = new HashMap<>();
        responseError.put("errores", error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);

    }
}
