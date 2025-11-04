/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.model.EstadosEquipo;
import com.example.InventarioPlus.service.EstadosEquipoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/api/estados_equipo")
public class EstadosEquipoController {
    

    private final EstadosEquipoService estadoEquipoService;

    public EstadosEquipoController(EstadosEquipoService estadoEquipoService) {
        this.estadoEquipoService = estadoEquipoService;
    }

    @GetMapping
    public List<EstadosEquipo> listarEstados() {
        return estadoEquipoService.listarEstados();
    }

    @GetMapping("/{id}")
    public Optional<EstadosEquipo> obtenerEstado(@PathVariable Integer id) {
        return estadoEquipoService.obtenerEstadoPorId(id);
    }

    @PostMapping
    public EstadosEquipo crearEstado(@RequestBody EstadosEquipo estado) {
        return estadoEquipoService.guardarEstado(estado);
    }

    @PutMapping("/{id}")
    public EstadosEquipo actualizarEstado(@PathVariable Integer id, @RequestBody EstadosEquipo estado) {
        return estadoEquipoService.obtenerEstadoPorId(id).map(e -> {
            e.setNombre(estado.getNombre());
            e.setDescripcion(estado.getDescripcion());
            e.setActivo(estado.getActivo());
            e.setUpdatedAt(estado.getUpdatedAt());
            return estadoEquipoService.guardarEstado(e);
        }).orElseThrow(() -> new RuntimeException("Estado no encontrado con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarEstado(@PathVariable Integer id) {
        estadoEquipoService.eliminarEstado(id);
        return "Estado eliminado con éxito.";
    }
}
