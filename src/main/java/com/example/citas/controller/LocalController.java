package com.example.citas.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.citas.dto.local.LocalRequestDTO;
import com.example.citas.services.LocalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/local")
public class LocalController {
    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> saveLocal(@Valid @RequestBody LocalRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.localService.saveLocal(requestDTO));
    }

}
