package com.example.InventarioPlus.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "modelos")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "marca_id", nullable = false)
    private Integer marcaId;

    @Column(length = 255, nullable = false)
    private String nombre;

    @Column(name = "codigo_modelo", length = 100)
    private String codigoModelo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Boolean activo;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Modelo() {}

    public Modelo(Integer marcaId, String nombre, String codigoModelo, String descripcion, Boolean activo) {
        this.marcaId = marcaId;
        this.nombre = nombre;
        this.codigoModelo = codigoModelo;
        this.descripcion = descripcion;
        this.activo = activo;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMarcaId() {
        return marcaId;
    }
    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoModelo() {
        return codigoModelo;
    }
    public void setCodigoModelo(String codigoModelo) {
        this.codigoModelo = codigoModelo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
