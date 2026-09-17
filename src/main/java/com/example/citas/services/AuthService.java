package com.example.citas.services;

import javax.management.relation.RoleNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.citas.dto.RoleResponse;
import com.example.citas.dto.UserRegisterResponse;
import com.example.citas.dto.UserRequest;
import com.example.citas.excepcion.CategoriaExcetoon;
import com.example.citas.models.Role;
import com.example.citas.models.Usuario;
import com.example.citas.repository.RoleRepository;
import com.example.citas.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository repository;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.repository = roleRepository;
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

}
