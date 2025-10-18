package com.example.InventarioPlus.repository;


import com.example.InventarioPlus.model.*;
import com.example.InventarioPlus.controller.*;
import com.example.InventarioPlus.repository.*;
import com.example.InventarioPlus.service.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipoRepository {
// Métodos de consulta personalizados
    
    // Buscar por número de serie (único)
    Optional<Equipo> findByNumeroSerial(String numeroSerial);
    
    // Buscar por estado
    List<Equipo> findByEstado(String estado);
    
    // Buscar por tipo
    List<Equipo> findByTipo(String tipo);
    
    // Buscar por ubicación
    List<Equipo> findByUbicacion(String ubicacion);
    
    // Buscar por nombre (contiene)
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar equipos que requieren inspección
    List<Equipo> findByRequiereInspeccion(String requiereInspeccion);
    
    // Buscar por tipo y estado
    List<Equipo> findByTipoAndEstado(String tipo, String estado);
    
    // Consulta personalizada para buscar por múltiples criterios
    @Query("SELECT e FROM Equipo e WHERE " +
           "(:nombre IS NULL OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
           "(:tipo IS NULL OR e.tipo = :tipo) AND " +
           "(:estado IS NULL OR e.estado = :estado) AND " +
           "(:ubicacion IS NULL OR e.ubicacion = :ubicacion)")
    List<Equipo> findByMultipleCriteria(@Param("nombre") String nombre, 
                                      @Param("tipo") String tipo, 
                                      @Param("estado") String estado, 
                                      @Param("ubicacion") String ubicacion);
    
    // Spring Data JPA ya nos da métodos como save, findAll, findById, deleteById
}
