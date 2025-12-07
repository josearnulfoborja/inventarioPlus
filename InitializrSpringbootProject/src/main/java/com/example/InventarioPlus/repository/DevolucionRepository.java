package com.example.InventarioPlus.repository;

import com.example.InventarioPlus.model.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Integer> {

    // Buscar devoluciones por ID de préstamo
    List<Devolucion> findByIdPrestamo(Integer idPrestamo);

    // Buscar devoluciones por especialista asignado
    List<Devolucion> findByEspecialistaAsignadoId(Integer especialistaAsignadoId);

    // Buscar devoluciones por estado de inspección
    List<Devolucion> findByEstadoInspeccion(String estadoInspeccion);

    // Buscar devoluciones que solicitan inspección
    List<Devolucion> findBySolicitarInspeccion(String solicitarInspeccion);
}
