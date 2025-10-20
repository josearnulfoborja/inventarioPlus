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
@Table(name = "evaluaciones_tecnicas")
public class EvaluacionTecnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluacion;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    private Boolean aprobado;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public EvaluacionTecnica() {}

    public EvaluacionTecnica(String observaciones, Boolean aprobado, Equipo equipo, Usuario usuario) {
        this.observaciones = observaciones;
        this.aprobado = aprobado;
        this.equipo = equipo;
        this.usuario = usuario;
    }

    public Long getIdEvaluacion() {
        return idEvaluacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setIdEvaluacion(Long idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   
}
