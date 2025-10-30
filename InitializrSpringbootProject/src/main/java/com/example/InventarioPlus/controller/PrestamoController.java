/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.controller;

/**
 *
 * @author User
 */

import com.example.InventarioPlus.model.Prestamo;
import com.example.InventarioPlus.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> listarPrestamos() {
        return prestamoService.listarPrestamos();
    }

    @GetMapping("/{id}")
    public Optional<Prestamo> obtenerPrestamo(@PathVariable Long id) {
        return prestamoService.obtenerPrestamoPorId(id);
    }

    @PostMapping
    public Prestamo crearPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.guardarPrestamo(prestamo);
    }

    @PutMapping("/{id}")
    public Prestamo actualizarPrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        return prestamoService.obtenerPrestamoPorId(id).map(p -> {
            p.setFechaEntrega(prestamo.getFechaEntrega());
            p.setFechaDevolucion(prestamo.getFechaDevolucion());
            p.setFechaPrevista(prestamo.getFechaPrevista());
            p.setCostoTotal(prestamo.getCostoTotal());
            p.setEstadoPrestamo(prestamo.getEstadoPrestamo());
            p.setCliente(prestamo.getCliente());
            p.setEquipo(prestamo.getEquipo());
            p.setEspecialista(prestamo.getEspecialista());
            return prestamoService.guardarPrestamo(p);
        }).orElseThrow(() -> new RuntimeException("Préstamo no encontrado con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarPrestamo(@PathVariable Long id) {
        prestamoService.eliminarPrestamo(id);
        return "Préstamo eliminado con éxito.";
    }
}
