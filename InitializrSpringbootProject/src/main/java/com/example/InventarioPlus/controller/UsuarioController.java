/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.controller;

/**
 *
 * @author User
 */

import com.example.InventarioPlus.model.Usuario;
import com.example.InventarioPlus.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final String UPLOAD_DIR = "uploads/imagenes/usuarios/";

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de uploads", e);
        }
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.obtenerUsuarioPorId(id).map(u -> {
            u.setNombre(usuario.getNombre());
            u.setApellido(usuario.getApellido());
            u.setCorreoElectronico(usuario.getCorreoElectronico());
            u.setTelefono(usuario.getTelefono());
            u.setUsername(usuario.getUsername());
            u.setPassword(usuario.getPassword());
            u.setRolId(usuario.getRolId());
            u.setActivo(usuario.getActivo());
            u.setFechaCreacion(usuario.getFechaCreacion());
            u.setFechaActualizacion(usuario.getFechaActualizacion());
            u.setDui(usuario.getDui());
            return usuarioService.guardarUsuario(u);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "Usuario eliminado con éxito.";
    }

    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfil(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();

        String username = authentication.getName();
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorUsername(username);

        if (!usuarioOpt.isPresent()) {
            response.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Usuario usuario = usuarioOpt.get();
        // No enviar la contraseña al frontend
        usuario.setPassword(null);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/perfil")
    public ResponseEntity<?> actualizarPerfil(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("correoElectronico") String correoElectronico,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen,
            Authentication authentication) throws IOException {

        Map<String, Object> response = new HashMap<>();

        // Obtener el usuario autenticado
        String username = authentication.getName();
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorUsername(username);

        if (!usuarioOpt.isPresent()) {
            response.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Usuario usuario = usuarioOpt.get();

        // Actualizar campos básicos
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreoElectronico(correoElectronico);

        // Actualizar contraseña si se proporciona
        if (password != null && !password.isBlank()) {
            usuario.setPassword(passwordEncoder.encode(password));
        }

        // Procesar imagen si se proporciona
        if (imagen != null && !imagen.isEmpty()) {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = username + "_" + imagen.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(imagen.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            usuario.setNombreImagen(filename);
        }

        usuario.setFechaActualizacion(LocalDateTime.now());
        Usuario usuarioActualizado = usuarioService.guardarUsuario(usuario);

        response.put("mensaje", "Perfil actualizado exitosamente");
        response.put("usuario", usuarioActualizado);

        return ResponseEntity.ok(response);
    }
}
