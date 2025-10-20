/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.service;

/**
 *
 * @author User
 */

import com.example.InventarioPlus.model.Prestamo;
import com.example.InventarioPlus.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> obtenerPrestamoPorId(Long id) {
        return prestamoRepository.findById(id);
    }

    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public void eliminarPrestamo(Long id) {
        prestamoRepository.deleteById(id);
    }
}
