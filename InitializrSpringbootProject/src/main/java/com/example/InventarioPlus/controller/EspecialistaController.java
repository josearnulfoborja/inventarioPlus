/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.controller;

/**
 *
 * @author User
 */
import com.example.InventarioPlus.model.Especialista;
import com.example.InventarioPlus.service.EspecialistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/especialistas")
public class EspecialistaController {

    private final EspecialistaService especialistaService;

    public EspecialistaController(EspecialistaService especialistaService) {
        this.especialistaService = especialistaService;
    }

    @GetMapping
    public List<Especialista> listarEspecialistas() {
        return especialistaService.listarEspecialistas();
    }

    @GetMapping("/{id}")
    public Optional<Especialista> obtenerEspecialista(@PathVariable Long id) {
        return especialistaService.obtenerEspecialistaPorId(id);
    }

    @PostMapping
    public Especialista crearEspecialista(@RequestBody Especialista especialista) {
        return especialistaService.guardarEspecialista(especialista);
    }

 @PutMapping("/{id}")
public Especialista actualizarEspecialista(@PathVariable Long id, @RequestBody Especialista especialista) {
    return especialistaService.obtenerEspecialistaPorId(id).map(e -> {
        e.setNombre(especialista.getNombre());
        e.setDocumento(especialista.getDocumento());
        e.setTelefono(especialista.getTelefono());
        e.setDireccion(especialista.getDireccion());
        e.setCorreo(especialista.getCorreo());
        e.setDisponibilidad(especialista.getDisponibilidad());
        e.setUsuarioId(especialista.getUsuarioId());
        e.setActivo(especialista.getActivo());
        e.setFechaActualizacion(especialista.getFechaActualizacion());
        return especialistaService.guardarEspecialista(e);
    }).orElseThrow(() -> new RuntimeException("Especialista no encontrado con id " + id));
}


    @DeleteMapping("/{id}")
    public String eliminarEspecialista(@PathVariable Long id) {
        especialistaService.eliminarEspecialista(id);
        return "Especialista eliminado con éxito.";
    }
}
