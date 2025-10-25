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
import java.time.LocalDateTime;

@Entity
@Table(name = "historial")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    private LocalDateTime fechaAccion;

    @Column(columnDefinition = "TEXT")
    private String textoAccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Historial() {}

    public Historial(LocalDateTime fechaAccion, String textoAccion, Usuario usuario) {
        this.fechaAccion = fechaAccion;
        this.textoAccion = textoAccion;
        this.usuario = usuario;
    }

    public Long getIdHistorial() {
        return idHistorial;
    }

    public LocalDateTime getFechaAccion() {
        return fechaAccion;
    }

    public String getTextoAccion() {
        return textoAccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public void setFechaAccion(LocalDateTime fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public void setTextoAccion(String textoAccion) {
        this.textoAccion = textoAccion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

