/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.service;

/**
 *
 * @author User
 */


import com.example.InventarioPlus.model.Especialista;
import com.example.InventarioPlus.repository.EspecialistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialistaService {

    private final EspecialistaRepository especialistaRepository;

    public EspecialistaService(EspecialistaRepository especialistaRepository) {
        this.especialistaRepository = especialistaRepository;
    }

    public List<Especialista> listarEspecialistas() {
        return especialistaRepository.findAll();
    }

    public Optional<Especialista> obtenerEspecialistaPorId(Long id) {
        return especialistaRepository.findById(id);
    }

    public Especialista guardarEspecialista(Especialista especialista) {
        return especialistaRepository.save(especialista);
    }

    public void eliminarEspecialista(Long id) {
        especialistaRepository.deleteById(id);
    }
}
