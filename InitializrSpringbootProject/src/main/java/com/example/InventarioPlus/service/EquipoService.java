/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.service;

/**
 *
 * @author User
 */

import com.example.InventarioPlus.model.Equipo;
import com.example.InventarioPlus.model.Modelo;
import com.example.InventarioPlus.model.TipoEquipo;
import com.example.InventarioPlus.model.EstadosEquipo;
import com.example.InventarioPlus.model.Ubicacion;
import com.example.InventarioPlus.model.Marca;
import com.example.InventarioPlus.repository.EquipoRepository;
import com.example.InventarioPlus.repository.ModeloRepository;
import com.example.InventarioPlus.repository.TipoEquipoRepository;
import com.example.InventarioPlus.repository.EstadosEquipoRepository;
import com.example.InventarioPlus.repository.UbicacionRepository;
import com.example.InventarioPlus.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final ModeloRepository modeloRepository;
    private final TipoEquipoRepository tipoEquipoRepository;
    private final EstadosEquipoRepository estadosEquipoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final MarcaRepository marcaRepository;

    public EquipoService(EquipoRepository equipoRepository,
            ModeloRepository modeloRepository,
            TipoEquipoRepository tipoEquipoRepository,
            EstadosEquipoRepository estadosEquipoRepository,
            UbicacionRepository ubicacionRepository,
            MarcaRepository marcaRepository) {
        this.equipoRepository = equipoRepository;
        this.modeloRepository = modeloRepository;
        this.tipoEquipoRepository = tipoEquipoRepository;
        this.estadosEquipoRepository = estadosEquipoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.marcaRepository = marcaRepository;
    }

    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id);
    }

    public Equipo guardarEquipo(Equipo equipo) {
        // Si es creación (id es null), establecer fecha de creación
        if (equipo.getId() == null && equipo.getFechaCreacion() == null) {
            equipo.setFechaCreacion(LocalDateTime.now());
        }

        // Siempre actualizar la fecha de actualización
        equipo.setFechaActualizacion(LocalDateTime.now());

        // Establecer valores por defecto para campos Boolean requeridos
        if (equipo.getRequiereEspecialista() == null) {
            equipo.setRequiereEspecialista(false);
        }
        if (equipo.getRequiereInspeccion() == null) {
            equipo.setRequiereInspeccion(false);
        }
        if (equipo.getActivo() == null) {
            equipo.setActivo(true);
        }

        // Resolver IDs transitorios a objetos si es necesario
        if (equipo.getModeloId() != null) {
            Optional<Modelo> modelo = modeloRepository.findById(equipo.getModeloId().intValue());
            modelo.ifPresent(equipo::setModelo);
        }
        if (equipo.getTipoId() != null) {
            Optional<TipoEquipo> tipo = tipoEquipoRepository.findById(equipo.getTipoId().intValue());
            tipo.ifPresent(equipo::setTipo);
        }
        if (equipo.getEstadoId() != null) {
            Optional<EstadosEquipo> estado = estadosEquipoRepository.findById(equipo.getEstadoId().intValue());
            estado.ifPresent(equipo::setEstadoEquipo);
        }
        if (equipo.getUbicacionId() != null) {
            Optional<Ubicacion> ubicacion = ubicacionRepository.findById(equipo.getUbicacionId().intValue());
            ubicacion.ifPresent(equipo::setUbicacion);
        }
        if (equipo.getMarcaId() != null) {
            Optional<Marca> marca = marcaRepository.findById(equipo.getMarcaId().intValue());
            marca.ifPresent(equipo::setMarca);
        }

        return equipoRepository.save(equipo);
    }

    public void eliminarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }
}
