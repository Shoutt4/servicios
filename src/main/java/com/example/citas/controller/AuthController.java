package com.example.citas.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.citas.dto.LoginRequest;
import com.example.citas.dto.LoginRespones;
import com.example.citas.dto.UpdateUserDTO;
import com.example.citas.dto.UserRegisterResponse;
import com.example.citas.dto.UserRequest;

import com.example.citas.security.JwtService;
import com.example.citas.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, AuthService authService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRespones> loginUser(@RequestBody LoginRequest request) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = this.jwtService.generateToken(authentication.getName(), authentication.getAuthorities()
                .iterator()
                .next().getAuthority().replace("ROLE_", ""));
        return ResponseEntity.ok(new LoginRespones(token));

    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.register(request));
    }

    @GetMapping("/detaills")
    public ResponseEntity<UserRegisterResponse> obtenerDetallesUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.authService.getUser(token));
    }

    @GetMapping("/me")
    public ResponseEntity<UserRegisterResponse> getMe(Authentication au) {
        return ResponseEntity.ok(this.authService.getUse(au));
    }

    @PatchMapping("/update")
    public ResponseEntity<Map<String, Object>> updateUser(Authentication authentication,
            @RequestBody UpdateUserDTO request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.authService.updateUser(authentication, request));
    }

}
