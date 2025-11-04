/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.service;

import com.example.InventarioPlus.model.EstadosEquipo;
import com.example.InventarioPlus.repository.EstadosEquipoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class EstadosEquipoService {
    private final EstadosEquipoRepository estadoEquipoRepository;

    public EstadosEquipoService(EstadosEquipoRepository estadoEquipoRepository) {
        this.estadoEquipoRepository = estadoEquipoRepository;
    }

    public List<EstadosEquipo> listarEstados() {
        return estadoEquipoRepository.findAll();
    }

    public Optional<EstadosEquipo> obtenerEstadoPorId(Integer id) {
        return estadoEquipoRepository.findById(id);
    }

    public EstadosEquipo guardarEstado(EstadosEquipo estado) {
        return estadoEquipoRepository.save(estado);
    }

    public void eliminarEstado(Integer id) {
        estadoEquipoRepository.deleteById(id);
    }
}
