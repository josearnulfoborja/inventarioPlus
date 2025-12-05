/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.service;

/**
 *
 * @author User
 */

import com.example.InventarioPlus.model.Usuario;
import com.example.InventarioPlus.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // ⭐ AGREGAR ESTO

    // ⭐ MODIFICAR: Inyectar PasswordEncoder
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // ⭐ MODIFICAR: Hashear contraseña antes de guardar
    public Usuario guardarUsuario(Usuario usuario) {
        // Si es un usuario nuevo O si se está actualizando la contraseña
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            // Verificar si la contraseña ya está hasheada (empieza con $2a$ o $2b$)
            if (!usuario.getPassword().startsWith("$2a$") && !usuario.getPassword().startsWith("$2b$")) {
                // Si NO está hasheada, hashearla ahora
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
        } else if (usuario.getIdUsuario() == null) {
            // Si es usuario nuevo y no tiene contraseña, lanzar error
            throw new IllegalArgumentException("La contraseña es requerida para crear un nuevo usuario");
        }
        // Si es actualización y password está vacío, se mantiene la contraseña actual
        
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario por ID
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
