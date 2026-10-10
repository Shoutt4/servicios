package com.example.citas.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.citas.dto.LoginRequest;
import com.example.citas.dto.LoginRespones;
import com.example.citas.dto.UpdateUserDTO;
import com.example.citas.dto.UserRegisterResponse;
import com.example.citas.dto.UserRequest;
import com.example.citas.dto.auth.AuthResponse;
import com.example.citas.dto.auth.RequestAuth;
import com.example.citas.security.JwtService;
import com.example.citas.services.AuthService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;


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

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/byWitch")
    public ResponseEntity<List<AuthResponse>> getWitch(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.authService.getUserWitch(name));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/userIgnorecase")
    public ResponseEntity<List<AuthResponse>> getUserByNameIgnoreCase(
            @Valid @RequestParam(name = "nombre", required = true) String name) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.authService.getUserIgnoreCaseByName(name));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ignoreCaseContent")
    public ResponseEntity<List<AuthResponse>> getIncludeIgnoreCaseCointainig(
            @Valid @RequestParam(name = "parametro", required = true) String name) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(this.authService.getUsuariosByCointainigNameIgoneCase(name));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/createAt")
    public ResponseEntity<List<AuthResponse>> getUserByCreateAt(
            @Valid @RequestParam(name = "fechaCreacion", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fecha) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.authService.getUsersByCreateAt(fecha));

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/nameAndRole")
    public ResponseEntity<List<AuthResponse>> getUserByRoleAndName(@Valid @RequestParam(required = true) String name,
            @RequestParam(required = true) String role) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.authService.getByNameAndRole(name, role));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/byNameAndEmail")
    public ResponseEntity<List<AuthResponse>> getByNameAndEmai(@Valid @RequestBody RequestAuth request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.authService.getUserByNameAndEmail(request));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ByNameOrEmail")
    public ResponseEntity<List<AuthResponse>> getUserByNameOrEmail(@Valid @RequestBody RequestAuth request) {
        return ResponseEntity.status(200).body(this.authService.getUserByNameOrEmail(request));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/exists")
    public ResponseEntity<Map<String, Object>> existByEmail(@Valid @RequestParam String email) {
        return ResponseEntity.status(200).body(this.authService.existByEmail(email));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/countByEmail")
    public ResponseEntity<Map<String, Object>> countByCharacter(@Valid @RequestParam(required = true) char character) {
        return ResponseEntity.status(200).body(this.authService.coutByEmail(character));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/byRole")
    public ResponseEntity<List<AuthResponse>> getUserByRole(
            @Valid @RequestParam(name = "role") @NotBlank(message = "el rol no debe ser nulo") @Size(min = 4, max = 16, message = "rol debe ser entre 4 a 16 caracteres") String role) {
        return ResponseEntity.status(200).body(this.authService.getUserByRole(role));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/countByRole")

    public ResponseEntity<Map<String, Object>> countUsersByRole(
            @Valid @RequestParam @NotBlank(message = "el rol no debe ser nulo") String role) {
        return ResponseEntity.status(200).body(this.authService.countByRole(role));
    }
}
