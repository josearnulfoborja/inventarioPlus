package com.example.InventarioPlus.service;
import com.example.InventarioPlus.model.*;
import com.example.InventarioPlus.controller.*;
import com.example.InventarioPlus.repository.*;
import com.example.InventarioPlus.service.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@ Service
public class UsuarioService {
private final UsuarioRepository usuarioRepository;

    // Inyección de dependencias por constructor
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
