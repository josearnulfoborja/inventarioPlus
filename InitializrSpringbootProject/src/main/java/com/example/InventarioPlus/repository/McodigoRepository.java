/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.InventarioPlus.repository;

import com.example.InventarioPlus.model.MCodigos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *
 * @author User
 */
public interface McodigoRepository extends JpaRepository<MCodigos, Integer> {
    List<MCodigos> findByGrupoIgnoreCaseAndActivoTrueOrderByOrdenAsc(String grupo);
}
