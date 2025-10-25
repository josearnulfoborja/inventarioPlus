/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.service;

/**
 *
 * @author User
 */

import com.example.InventarioPlus.model.EvaluacionTecnica;
import com.example.InventarioPlus.repository.EvaluacionTecnicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionTecnicaService {

    private final EvaluacionTecnicaRepository evaluacionRepository;

    public EvaluacionTecnicaService(EvaluacionTecnicaRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public List<EvaluacionTecnica> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Optional<EvaluacionTecnica> obtenerEvaluacionPorId(Long id) {
        return evaluacionRepository.findById(id);
    }

    public EvaluacionTecnica guardarEvaluacion(EvaluacionTecnica evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public void eliminarEvaluacion(Long id) {
        evaluacionRepository.deleteById(id);
    }
}
