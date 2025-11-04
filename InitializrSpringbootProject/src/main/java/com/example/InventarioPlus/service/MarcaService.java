package com.example.InventarioPlus.service;

import com.example.InventarioPlus.model.Marca;
import com.example.InventarioPlus.repository.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> obtenerMarcaPorId(Integer id) {
        return marcaRepository.findById(id);
    }

    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void eliminarMarca(Integer id) {
        marcaRepository.deleteById(id);
    }
}
