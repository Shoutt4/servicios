package com.example.citas.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.citas.dto.RoleResponse;
import com.example.citas.dto.UpdateUserDTO;
import com.example.citas.dto.UserRegisterResponse;
import com.example.citas.dto.UserRequest;
import com.example.citas.excepcion.AuthException;
import com.example.citas.excepcion.CategoriaExcetoon;
import com.example.citas.models.Role;
import com.example.citas.models.Usuario;
import com.example.citas.repository.RoleRepository;
import com.example.citas.repository.UsuarioRepository;
import com.example.citas.security.JwtService;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository repository;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.repository = roleRepository;
        this.jwtService = jwtService;
    }

    public Usuario convertirRequest(UserRequest request) {
        return new Usuario(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()),
                request.getPhone());
    }

    public UserRegisterResponse convertirResponse(Usuario usuario) {
        RoleResponse rol = new RoleResponse(usuario.getRole().getId(), usuario.getRole().getName());
        return new UserRegisterResponse(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getPhone(),
                rol);
    }

    public UserRegisterResponse register(UserRequest request) {
        if (!this.usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            Role rolUserNew = this.repository.findByNameIgnoreCase("user")
                    .orElseThrow(() -> new CategoriaExcetoon("no se encontro rol useer"));
            return convertirResponse(this.usuarioRepository.save(new Usuario(request.getName(), request.getEmail(),
                    passwordEncoder.encode(request.getPassword()), request.getPhone(), rolUserNew)));
        } else {
            throw new IllegalArgumentException("el gemail ya esta registrado");
        }

    }

    public UserRegisterResponse getUser(String token) {
        Usuario user = null;
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            user = this.usuarioRepository.findByEmail(this.jwtService.getName(token))
                    .orElseThrow(() -> new IllegalArgumentException("token invalido"));
            return convertirResponse(user);
        } else {
            throw new IllegalArgumentException("TOKEN INVALIDO");
        }
    }

    public UserRegisterResponse getUse(Authentication authentication) {
        String username = authentication.getName();
        Usuario us = this.usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new AuthException("usuario no logeado"));
        return convertirResponse(us);

    }

    public Map<String, Object> updateUser(Authentication authException, UpdateUserDTO request) {
        Map<String, Object> res = new HashMap<>();
        Usuario us = this.usuarioRepository.findByEmail(authException.getName())
                .orElseThrow(() -> new AuthException("usuario no encontrado"));
        if (request != null) {
            us.setName(request.getNombre());
            us.setEmail(request.getCorreo());
            us.setPhone((request.getPhone()));
            res.put("estatus", "usuario actualizado");
            res.put("data", convertirResponse(this.usuarioRepository.save(us)));
            return res;
        } else {
            throw new AuthException("error");
        }
    }

}
