/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.model;

/**
 *
 * @author User
 */

import jakarta.persistence.*;

@Entity
@Table(name = "especialista")
public class Especialista_v2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecialista;

    private String nombre;
    private Boolean disponibilidad;

    public Especialista_v2() {}

    public Especialista_v2(String nombre, Boolean disponibilidad) {
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
    }

    public Long getIdEspecialista() {
        return idEspecialista;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setIdEspecialista(Long idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

   
}
