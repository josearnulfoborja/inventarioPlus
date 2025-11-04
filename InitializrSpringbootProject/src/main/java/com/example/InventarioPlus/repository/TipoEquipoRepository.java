/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.InventarioPlus.repository;

import com.example.InventarioPlus.model.TipoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface TipoEquipoRepository  extends JpaRepository<TipoEquipo, Integer> {
    
}
