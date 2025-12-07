/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.service;

/**
 *
 * @author User
 */

import com.example.InventarioPlus.model.MCodigos;
import com.example.InventarioPlus.repository.McodigoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class McodigoService {

    private final McodigoRepository mCodigoRepository;

    public McodigoService(McodigoRepository mCodigoRepository) {
        this.mCodigoRepository = mCodigoRepository;
    }

    public List<MCodigos> listarCodigos() {
        return mCodigoRepository.findAll();
    }

    public List<MCodigos> listarCodigosPorGrupo(String grupo) {
        return mCodigoRepository.findByGrupoIgnoreCaseAndActivoTrueOrderByOrdenAsc(grupo);
    }

    public Optional<MCodigos> obtenerCodigoPorId(Integer id) {
        return mCodigoRepository.findById(id);
    }

    public MCodigos guardarCodigo(MCodigos codigo) {
        return mCodigoRepository.save(codigo);
    }

    public void eliminarCodigo(Integer id) {
        mCodigoRepository.deleteById(id);
    }
}
