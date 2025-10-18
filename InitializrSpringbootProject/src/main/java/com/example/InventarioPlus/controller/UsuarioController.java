package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.model.*;
import com.example.InventarioPlus.controller.*;
import com.example.InventarioPlus.repository.*;
import com.example.InventarioPlus.service.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
  private final UsuarioService usuarioService;

    // Inyección de dependencias por constructor
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET: listar todos
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // GET: buscar por ID
    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // POST: crear nuevo usuario
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    // PUT: actualizar usuario
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.obtenerUsuarioPorId(id).map(u -> {
            u.setNombre(usuario.getNombre());
            u.setCorreo(usuario.getCorreo());
            return usuarioService.guardarUsuario(u);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

    // DELETE: eliminar usuario
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "Usuario eliminado con éxito.";
    }
}
