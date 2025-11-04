package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.model.Ubicacion;
import com.example.InventarioPlus.service.UbicacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @GetMapping
    public List<Ubicacion> listarUbicaciones() {
        return ubicacionService.listarUbicaciones();
    }

    @GetMapping("/{id}")
    public Optional<Ubicacion> obtenerUbicacion(@PathVariable Integer id) {
        return ubicacionService.obtenerUbicacionPorId(id);
    }

    @PostMapping
    public Ubicacion crearUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.guardarUbicacion(ubicacion);
    }

    @PutMapping("/{id}")
    public Ubicacion actualizarUbicacion(@PathVariable Integer id, @RequestBody Ubicacion ubicacion) {
        return ubicacionService.obtenerUbicacionPorId(id).map(u -> {
            u.setCodigo(ubicacion.getCodigo());
            u.setNombre(ubicacion.getNombre());
            u.setTipo(ubicacion.getTipo());
            u.setDireccion(ubicacion.getDireccion());
            u.setPiso(ubicacion.getPiso());
            u.setSector(ubicacion.getSector());
            u.setLat(ubicacion.getLat());
            u.setLng(ubicacion.getLng());
            u.setResponsable(ubicacion.getResponsable());
            u.setTelefono(ubicacion.getTelefono());
            u.setActivo(ubicacion.getActivo());
            u.setUpdatedAt(ubicacion.getUpdatedAt());
            return ubicacionService.guardarUbicacion(u);
        }).orElseThrow(() -> new RuntimeException("Ubicación no encontrada con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarUbicacion(@PathVariable Integer id) {
        ubicacionService.eliminarUbicacion(id);
        return "Ubicación eliminada con éxito.";
    }
}
