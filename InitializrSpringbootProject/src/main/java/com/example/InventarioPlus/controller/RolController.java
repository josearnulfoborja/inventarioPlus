/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.controller;

/**
 *
 * @author User
 */
import com.example.InventarioPlus.model.Rol;
import com.example.InventarioPlus.service.RolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public List<Rol> listarRoles() {
        return rolService.listarRoles();
    }

    @GetMapping("/{id}")
    public Optional<Rol> obtenerRol(@PathVariable Integer id) {
        return rolService.obtenerRolPorId(id);
    }

    @PostMapping
    public Rol crearRol(@RequestBody Rol rol) {
        return rolService.guardarRol(rol);
    }

    @PutMapping("/{id}")
    public Rol actualizarRol(@PathVariable Integer id, @RequestBody Rol rol) {
        return rolService.obtenerRolPorId(id).map(r -> {
            r.setNombreRol(rol.getNombreRol());
            r.setDescripcion(rol.getDescripcion());
            return rolService.guardarRol(r);
        }).orElseThrow(() -> new RuntimeException("Rol no encontrado con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarRol(@PathVariable Integer id) {
        rolService.eliminarRol(id);
        return "Rol eliminado con éxito.";
    }
}
