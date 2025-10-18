package com.example.InventarioPlus.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipos")
public class Equipo {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Long idEquipo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo", length = 100)
    private String tipo;

    @Column(name = "modelo", length = 100)
    private String modelo;

    @Column(name = "numero_serial", unique = true, length = 100)
    private String numeroSerial;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "ubicacion", length = 100)
    private String ubicacion;

    @Column(name = "requiere_inspeccion", length = 10)
    private String requiereInspeccion;

    @Column(name = "imagen", length = 500)
    private String imagen;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // Constructor vacío (obligatorio para JPA)
    public Equipo() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Constructor con parámetros básicos
    public Equipo(String nombre, String tipo, String modelo, String numeroSerial, String estado, String ubicacion, String requiereInspeccion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.modelo = modelo;
        this.numeroSerial = numeroSerial;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.requiereInspeccion = requiereInspeccion;
        this.imagen = null;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Constructor con todos los parámetros incluyendo imagen
    public Equipo(String nombre, String tipo, String modelo, String numeroSerial, String estado, String ubicacion, String requiereInspeccion, String imagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.modelo = modelo;
        this.numeroSerial = numeroSerial;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.requiereInspeccion = requiereInspeccion;
        this.imagen = imagen;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Métodos para actualizar fecha de modificación
    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.fechaCreacion = now;
        this.fechaActualizacion = now;
    }

    // Getters y setters
    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerial() {
        return numeroSerial;
    }

    public void setNumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getRequiereInspeccion() {
        return requiereInspeccion;
    }

    public void setRequiereInspeccion(String requiereInspeccion) {
        this.requiereInspeccion = requiereInspeccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", numeroSerial='" + numeroSerial + '\'' +
                ", estado='" + estado + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", requiereInspeccion='" + requiereInspeccion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaActualizacion=" + fechaActualizacion +
                '}';
    }
}