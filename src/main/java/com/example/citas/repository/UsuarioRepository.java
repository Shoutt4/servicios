package com.example.citas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.citas.models.Role;
import com.example.citas.models.Usuario;
import java.time.LocalDateTime;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String name);

    List<Usuario> findByNameStartingWith(String name);

    List<Usuario> findByNameIgnoringCase(String name);

    List<Usuario> findByNameContainingIgnoreCase(String name);

    List<Usuario> findByCreateAtGreaterThan(LocalDateTime createAt);

    List<Usuario> findByNameAndRoleName(String name, String role);
}
