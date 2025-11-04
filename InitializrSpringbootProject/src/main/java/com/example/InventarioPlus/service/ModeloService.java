package com.example.InventarioPlus.service;

import com.example.InventarioPlus.model.Modelo;
import com.example.InventarioPlus.repository.ModeloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;

    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    public List<Modelo> listarModelos() {
        return modeloRepository.findAll();
    }

    public Optional<Modelo> obtenerModeloPorId(Integer id) {
        return modeloRepository.findById(id);
    }

    public Modelo guardarModelo(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public void eliminarModelo(Integer id) {
        modeloRepository.deleteById(id);
    }
}
