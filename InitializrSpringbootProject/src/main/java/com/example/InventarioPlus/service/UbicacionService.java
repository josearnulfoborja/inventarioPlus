package com.example.InventarioPlus.service;

import com.example.InventarioPlus.model.Ubicacion;
import com.example.InventarioPlus.repository.UbicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public List<Ubicacion> listarUbicaciones() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> obtenerUbicacionPorId(Integer id) {
        return ubicacionRepository.findById(id);
    }

    public Ubicacion guardarUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public void eliminarUbicacion(Integer id) {
        ubicacionRepository.deleteById(id);
    }
}
