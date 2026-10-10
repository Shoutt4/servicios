package com.example.citas.services;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.citas.dto.RoleResponse;
import com.example.citas.dto.UpdateUserDTO;
import com.example.citas.dto.UserRegisterResponse;
import com.example.citas.dto.UserRequest;
import com.example.citas.dto.UsuarioResponse;
import com.example.citas.excepcion.AuthException;
import com.example.citas.excepcion.CategoriaExcetoon;
import com.example.citas.models.Role;
import com.example.citas.models.Usuario;
import com.example.citas.repository.RoleRepository;
import com.example.citas.repository.UsuarioRepository;
import com.example.citas.security.JwtService;
import com.example.citas.dto.auth.AuthResponse;
import com.example.citas.dto.auth.RequestAuth;
import com.example.citas.excepcion.ErrorGlobal;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }

    public Usuario convertirRequest(UserRequest request) {
        return new Usuario(request.getName(), request.getName(), passwordEncoder.encode(request.getPassword()),
                request.getPhone());
    }

    public UserRegisterResponse convertirResponse(Usuario usuario) {
        RoleResponse rol = new RoleResponse(usuario.getRole().getId(), usuario.getRole().getName());
        return new UserRegisterResponse(usuario.getId_user(), usuario.getName(), usuario.getEmail(), usuario.getPhone(),
                rol);
    }

    public UserRegisterResponse register(UserRequest request) {
        if (!this.usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            Role rolUserNew = this.roleRepository.findByNameIgnoreCase("user")
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

    public AuthResponse converirResponse2(Usuario usuario) {
        RoleResponse response = new RoleResponse(usuario.getRole().getId(), usuario.getRole().getName());
        return AuthResponse.builder().id_user(usuario.getId_user()).name(usuario.getName()).email(usuario.getEmail())
                .role(response).createAt(usuario.getCreateAt()).build();
    }

    public List<AuthResponse> getUserWitch(String name) {
        if (name != null && name != "") {
            List<Usuario> users = this.usuarioRepository.findByNameStartingWith(name);
            if (users.size() != 0) {
                return this.usuarioRepository.findByNameStartingWith(name).stream().map(this::converirResponse2)
                        .toList();
            } else {
                throw new ErrorGlobal("402", "ningun usuario encontrado con ese nombre");
            }

        } else {
            throw new ErrorGlobal("404", "error de parametros");
        }
    }

    public List<AuthResponse> getUserIgnoreCaseByName(String name) {
        if (name != null && name != "") {
            List<Usuario> users = this.usuarioRepository.findByNameIgnoringCase(name);
            if (users.size() != 0) {
                return users.stream().map(this::converirResponse2).toList();
            } else {
                throw new ErrorGlobal("404", "no existe nungun usuario que tenga el nombre + " + name);
            }
        } else {
            throw new ErrorGlobal("400", "parametro name invalido null");
        }
    }

    public List<AuthResponse> getUsuariosByCointainigNameIgoneCase(String name) {

        if (name != null && name != "") {
            List<Usuario> users = this.usuarioRepository.findByNameContainingIgnoreCase(name);
            if (!users.isEmpty()) {
                return users.stream().map(this::converirResponse2).toList();
            } else {
                throw new ErrorGlobal("404", "no existen datos registrados con el nombre de " + name);
            }
        } else {
            throw new ErrorGlobal("400", "parametro name invalido");
        }
    }

    public List<AuthResponse> getUsersByCreateAt(LocalDateTime fecha) {
        if (!fecha.equals("fecha") && !fecha.equals(null)) {
            List<Usuario> users = this.usuarioRepository.findByCreateAtGreaterThan(fecha);
            if (users.size() != 0) {
                return users.stream().map(this::converirResponse2).toList();
            } else {
                throw new ErrorGlobal("404", "ningun usuario encontrado con fecha creada ");
            }

        } else {
            throw new ErrorGlobal("400", "parametro " + fecha + " invalido");
        }
    }

    public List<AuthResponse> getByNameAndRole(String name, String role) {

        List<Usuario> response = this.usuarioRepository.findByNameAndRoleName(name, role);

        if (!response.isEmpty()) {
            return response.stream().map(this::converirResponse2).toList();
        } else {
            throw new ErrorGlobal("400", "no existen usuarios con las especificaciones");
        }
    }

    public List<AuthResponse> getUserByNameAndEmail(RequestAuth reuest) {
        List<Usuario> users = this.usuarioRepository.findByNameAndEmailEquals(reuest.getName(), reuest.getEmail());
        if (users.size() != 0) {
            return users.stream().map(this::converirResponse2).toList();
        } else {
            throw new ErrorGlobal("404", "no existen datos con los parametros puestos");
        }
    }

    public List<AuthResponse> getUserByNameOrEmail(RequestAuth request) {
        List<Usuario> users = this.usuarioRepository.findByNameContainingOrEmailEquals(request.getName(),
                request.getEmail());
        if (!users.isEmpty()) {
            return users.stream().map(this::converirResponse2).toList();
        } else {
            throw new ErrorGlobal("404", "no existen datos con las especificaciones");
        }
    }

    public Map<String, Object> existByEmail(String email) {
        if (this.usuarioRepository.existsByEmail(email)) {
            return Map.of("succes", true, "data", "el usuario existe con correo  " + email);
        } else {
            throw new ErrorGlobal("404", "no existe ningu usuario con el correo :" + email);
        }
    }

    public Map<String, Object> coutByEmail(char caracter) {
        int res = this.usuarioRepository.countByNameStartsWith(caracter);
        if (res != 0) {
            return Map.of("succes", true, "cantidad de usuarios", res);
        } else {
            throw new ErrorGlobal("404", "no existe ningun suario con ese caracter");
        }
    }

    public List<AuthResponse> getUserByRole(String role) {
        if (this.usuarioRepository.existsByRole_Name(role)) {
            return this.usuarioRepository.findByRole_NameIs(role).stream().map(this::converirResponse2).toList();
        } else {
            throw new ErrorGlobal("404", "ningun usuario existente con ese rol");
        }
    }

    public Map<String, Object> countByRole(String role) {
        if (this.usuarioRepository.existsByRole_Name(role)) {
            return Map.of("succes", true,
                    "cantidad usuarios", this.usuarioRepository.countByRole_Name(role));
        } else {
            throw new ErrorGlobal("404", "no esiste ninguna cantidad registardos con ese rol");
        }
    }
}
