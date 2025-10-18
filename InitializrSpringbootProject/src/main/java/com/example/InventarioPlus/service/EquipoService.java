package com.example.InventarioPlus.service;


import com.example.InventarioPlus.model.*;
import com.example.InventarioPlus.controller.*;
import com.example.InventarioPlus.repository.*;
import com.example.InventarioPlus.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EquipoService {

    private final EquipoRepository equipoRepository;

    // Inyección de dependencias por constructor
    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    // Métodos básicos CRUD
    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id);
    }

    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public void eliminarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }

    // Métodos de búsqueda específicos
    
    public Optional<Equipo> buscarPorNumeroSerial(String numeroSerial) {
        return equipoRepository.findByNumeroSerial(numeroSerial);
    }

    public List<Equipo> buscarPorEstado(String estado) {
        return equipoRepository.findByEstado(estado);
    }

    public List<Equipo> buscarPorTipo(String tipo) {
        return equipoRepository.findByTipo(tipo);
    }

    public List<Equipo> buscarPorUbicacion(String ubicacion) {
        return equipoRepository.findByUbicacion(ubicacion);
    }

    public List<Equipo> buscarPorNombre(String nombre) {
        return equipoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Equipo> buscarEquiposQueRequierenInspeccion(String requiereInspeccion) {
        return equipoRepository.findByRequiereInspeccion(requiereInspeccion);
    }

    public List<Equipo> buscarPorTipoYEstado(String tipo, String estado) {
        return equipoRepository.findByTipoAndEstado(tipo, estado);
    }

    public List<Equipo> buscarPorCriteriosMultiples(String nombre, String tipo, String estado, String ubicacion) {
        return equipoRepository.findByMultipleCriteria(nombre, tipo, estado, ubicacion);
    }

    // Método para actualizar equipo completo
    public Equipo actualizarEquipo(Long id, Equipo equipoActualizado) {
        return equipoRepository.findById(id).map(equipo -> {
            equipo.setNombre(equipoActualizado.getNombre());
            equipo.setTipo(equipoActualizado.getTipo());
            equipo.setModelo(equipoActualizado.getModelo());
            equipo.setNumeroSerial(equipoActualizado.getNumeroSerial());
            equipo.setEstado(equipoActualizado.getEstado());
            equipo.setUbicacion(equipoActualizado.getUbicacion());
            equipo.setRequiereInspeccion(equipoActualizado.getRequiereInspeccion());
            equipo.setImagen(equipoActualizado.getImagen());
            return equipoRepository.save(equipo);
        }).orElseThrow(() -> new RuntimeException("Equipo no encontrado con id " + id));
    }

    // Método para verificar si existe un equipo por número de serie
    public boolean existeNumeroSerial(String numeroSerial) {
        return equipoRepository.findByNumeroSerial(numeroSerial).isPresent();
    }

    // Método para contar equipos por estado
    public long contarPorEstado(String estado) {
        return equipoRepository.findByEstado(estado).size();
    }
}