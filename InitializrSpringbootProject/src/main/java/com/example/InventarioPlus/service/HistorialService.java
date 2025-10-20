/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.service;

/**
 *
 * @author User
 */


import com.example.InventarioPlus.model.Historial;
import com.example.InventarioPlus.repository.HistorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialService {

    private final HistorialRepository historialRepository;

    public HistorialService(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public List<Historial> listarHistorial() {
        return historialRepository.findAll();
    }

    public Optional<Historial> obtenerHistorialPorId(Long id) {
        return historialRepository.findById(id);
    }

    public Historial guardarHistorial(Historial historial) {
        return historialRepository.save(historial);
    }

    public void eliminarHistorial(Long id) {
        historialRepository.deleteById(id);
    }
}
