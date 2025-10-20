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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipo;

    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private String estado;
    private BigDecimal costoDia;
    private LocalDate fechaDisponible;
    private LocalDateTime fechaUsoHora;
    private Boolean requiereEspecialista;

    public Equipo() {}

    public Equipo(String nombre, String descripcion, String estado, BigDecimal costoDia,
                  LocalDate fechaDisponible, LocalDateTime fechaUsoHora, Boolean requiereEspecialista) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.costoDia = costoDia;
        this.fechaDisponible = fechaDisponible;
        this.fechaUsoHora = fechaUsoHora;
        this.requiereEspecialista = requiereEspecialista;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public BigDecimal getCostoDia() {
        return costoDia;
    }

    public LocalDate getFechaDisponible() {
        return fechaDisponible;
    }

    public LocalDateTime getFechaUsoHora() {
        return fechaUsoHora;
    }

    public Boolean getRequiereEspecialista() {
        return requiereEspecialista;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCostoDia(BigDecimal costoDia) {
        this.costoDia = costoDia;
    }

    public void setFechaDisponible(LocalDate fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
    }

    public void setFechaUsoHora(LocalDateTime fechaUsoHora) {
        this.fechaUsoHora = fechaUsoHora;
    }

    public void setRequiereEspecialista(Boolean requiereEspecialista) {
        this.requiereEspecialista = requiereEspecialista;
    }

    
}

