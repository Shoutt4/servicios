package com.example.citas.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.citas.excepcion.AuthException;
import com.example.citas.models.Usuario;
import com.example.citas.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByName(username)
                .orElseThrow(() -> new AuthException("Usuario no registrado"));

        return User.withUsername(usuario.getName()).password(usuario.getPassword()).roles(usuario.getRole().getName())
                .build();
    }
}
