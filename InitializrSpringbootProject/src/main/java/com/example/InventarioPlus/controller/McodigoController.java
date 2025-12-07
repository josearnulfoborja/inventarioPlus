/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.controller;

/**
 *
 * @author User
 */

import com.example.InventarioPlus.model.MCodigos;
import com.example.InventarioPlus.service.McodigoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mcodigos")
public class McodigoController {

    private final McodigoService mCodigoService;

    public McodigoController(McodigoService mCodigoService) {
        this.mCodigoService = mCodigoService;
    }

    @GetMapping
    public List<MCodigos> listarCodigos(@RequestParam(value = "grupo", required = false) String grupo) {
        if (grupo != null && !grupo.trim().isEmpty()) {
            return mCodigoService.listarCodigosPorGrupo(grupo.trim());
        }
        return mCodigoService.listarCodigos();
    }

    @GetMapping("/{id}")
    public Optional<MCodigos> obtenerCodigo(@PathVariable Integer id) {
        return mCodigoService.obtenerCodigoPorId(id);
    }

    @PostMapping
    public MCodigos crearCodigo(@RequestBody MCodigos codigo) {
        return mCodigoService.guardarCodigo(codigo);
    }

    @PutMapping("/{id}")
    public MCodigos actualizarCodigo(@PathVariable Integer id, @RequestBody MCodigos codigo) {
        return mCodigoService.obtenerCodigoPorId(id).map(c -> {
            c.setGrupo(codigo.getGrupo());
            c.setCodigo(codigo.getCodigo());
            c.setNombre(codigo.getNombre());
            c.setDescripcion(codigo.getDescripcion());
            c.setOrden(codigo.getOrden());
            c.setActivo(codigo.getActivo());
            c.setUpdatedAt(codigo.getUpdatedAt());
            return mCodigoService.guardarCodigo(c);
        }).orElseThrow(() -> new RuntimeException("Código no encontrado con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarCodigo(@PathVariable Integer id) {
        mCodigoService.eliminarCodigo(id);
        return "Código eliminado con éxito.";
    }
}
