package com.example.citas.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.citas.dto.UserRegisterResponse;
import com.example.citas.dto.UserRequest;
import com.example.citas.models.Usuario;
import com.example.citas.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario convertirRequest(UserRequest request) {
        return new Usuario(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()),
                request.getPhone());
    }

    public UserRegisterResponse convertirResponse(Usuario usuario) {
        return new UserRegisterResponse(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getPhone());
    }

    public UserRegisterResponse register(UserRequest request) {

        return convertirResponse(this.usuarioRepository.save(new Usuario(request.getName(), request.getEmail(),
                passwordEncoder.encode(request.getPassword()), request.getPhone())));
    }

}
