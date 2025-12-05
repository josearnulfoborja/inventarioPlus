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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
}
