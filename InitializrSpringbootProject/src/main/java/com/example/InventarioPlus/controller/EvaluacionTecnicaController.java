/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.controller;

/**
 *
 * @author User
 */
import com.example.InventarioPlus.model.EvaluacionTecnica;
import com.example.InventarioPlus.service.EvaluacionTecnicaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionTecnicaController {

    private final EvaluacionTecnicaService evaluacionService;

    public EvaluacionTecnicaController(EvaluacionTecnicaService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @GetMapping
    public List<EvaluacionTecnica> listarEvaluaciones() {
        return evaluacionService.listarEvaluaciones();
    }

    @GetMapping("/{id}")
    public Optional<EvaluacionTecnica> obtenerEvaluacion(@PathVariable Long id) {
        return evaluacionService.obtenerEvaluacionPorId(id);
    }

    @PostMapping
    public EvaluacionTecnica crearEvaluacion(@RequestBody EvaluacionTecnica evaluacion) {
        return evaluacionService.guardarEvaluacion(evaluacion);
    }

    @PutMapping("/{id}")
    public EvaluacionTecnica actualizarEvaluacion(@PathVariable Long id, @RequestBody EvaluacionTecnica evaluacion) {
        return evaluacionService.obtenerEvaluacionPorId(id).map(e -> {
            e.setObservaciones(evaluacion.getObservaciones());
            e.setAprobado(evaluacion.getAprobado());
            e.setEquipo(evaluacion.getEquipo());
            e.setUsuario(evaluacion.getUsuario());
            return evaluacionService.guardarEvaluacion(e);
        }).orElseThrow(() -> new RuntimeException("Evaluación no encontrada con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarEvaluacion(@PathVariable Long id) {
        evaluacionService.eliminarEvaluacion(id);
        return "Evaluación eliminada con éxito.";
    }
}
