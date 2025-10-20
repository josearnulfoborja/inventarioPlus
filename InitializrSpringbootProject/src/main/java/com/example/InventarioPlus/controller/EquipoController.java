/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.controller;


/**
 *
 * @author User
 */
import com.example.InventarioPlus.model.Equipo;
import com.example.InventarioPlus.service.EquipoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    public List<Equipo> listarEquipos() {
        return equipoService.listarEquipos();
    }

    @GetMapping("/{id}")
    public Optional<Equipo> obtenerEquipo(@PathVariable Long id) {
        return equipoService.obtenerEquipoPorId(id);
    }

    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo) {
        return equipoService.guardarEquipo(equipo);
    }

    @PutMapping("/{id}")
    public Equipo actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        return equipoService.obtenerEquipoPorId(id).map(e -> {
            e.setNombre(equipo.getNombre());
            e.setDescripcion(equipo.getDescripcion());
            e.setEstado(equipo.getEstado());
            e.setCostoDia(equipo.getCostoDia());
            e.setFechaDisponible(equipo.getFechaDisponible());
            e.setFechaUsoHora(equipo.getFechaUsoHora());
            e.setRequiereEspecialista(equipo.getRequiereEspecialista());
            return equipoService.guardarEquipo(e);
        }).orElseThrow(() -> new RuntimeException("Equipo no encontrado con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return "Equipo eliminado con éxito.";
    }
}
