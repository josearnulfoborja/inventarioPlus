/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.controller;

/**
 *
 * @author User
 */
import com.example.InventarioPlus.model.Historial;
import com.example.InventarioPlus.service.HistorialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    // GET: listar todo el historial
    @GetMapping
    public List<Historial> listarHistorial() {
        return historialService.listarHistorial();
    }

    // GET: obtener historial por ID
    @GetMapping("/{id}")
    public Optional<Historial> obtenerHistorial(@PathVariable Long id) {
        return historialService.obtenerHistorialPorId(id);
    }

    // POST: crear nuevo registro de historial
    @PostMapping
    public Historial crearHistorial(@RequestBody Historial historial) {
        return historialService.guardarHistorial(historial);
    }

    // PUT: actualizar historial existente
    @PutMapping("/{id}")
    public Historial actualizarHistorial(@PathVariable Long id, @RequestBody Historial historial) {
        return historialService.obtenerHistorialPorId(id).map(h -> {
            h.setFechaAccion(historial.getFechaAccion());
            h.setTextoAccion(historial.getTextoAccion());
            h.setUsuario(historial.getUsuario());
            return historialService.guardarHistorial(h);
        }).orElseThrow(() -> new RuntimeException("Historial no encontrado con id " + id));
    }

    // DELETE: eliminar historial
    @DeleteMapping("/{id}")
    public String eliminarHistorial(@PathVariable Long id) {
        historialService.eliminarHistorial(id);
        return "Historial eliminado con éxito.";
    }
}
