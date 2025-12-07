package com.example.InventarioPlus.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "devoluciones")
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucion")
    private Integer idDevolucion;

    @Column(name = "id_prestamo")
    private Long idPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_prestamo", insertable = false, updatable = false)
    private Prestamo prestamo;

    @Column(name = "fecha_registro_devolucion")
    private LocalDateTime fechaRegistroDevolucion;

    @Column(name = "fecha_devolucion_real")
    private LocalDateTime fechaDevolucionReal;

    @Column(name = "condicion_al_devolver", length = 50)
    private String condicionAlDevolver;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "solicitar_inspeccion", length = 10)
    private String solicitarInspeccion;

    @Column(name = "especialista_asignado_id")
    private Integer especialistaAsignadoId;

    @Column(name = "fecha_inspeccion_programada")
    private LocalDateTime fechaInspeccionProgramada;

    @Column(name = "inspeccion_realizada", length = 10)
    private String inspeccionRealizada;

    @Column(name = "estado_inspeccion", length = 50)
    private String estadoInspeccion;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public Devolucion() {
    }

    public Devolucion(Long idPrestamo, LocalDateTime fechaRegistroDevolucion, LocalDateTime fechaDevolucionReal,
            String condicionAlDevolver, String observaciones, String solicitarInspeccion,
            Integer especialistaAsignadoId, LocalDateTime fechaInspeccionProgramada,
            String inspeccionRealizada, String estadoInspeccion, Integer creadoPor,
            LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.idPrestamo = idPrestamo;
        this.fechaRegistroDevolucion = fechaRegistroDevolucion;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.condicionAlDevolver = condicionAlDevolver;
        this.observaciones = observaciones;
        this.solicitarInspeccion = solicitarInspeccion;
        this.especialistaAsignadoId = especialistaAsignadoId;
        this.fechaInspeccionProgramada = fechaInspeccionProgramada;
        this.inspeccionRealizada = inspeccionRealizada;
        this.estadoInspeccion = estadoInspeccion;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters y Setters
    public Integer getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(Integer idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDateTime getFechaRegistroDevolucion() {
        return fechaRegistroDevolucion;
    }

    public void setFechaRegistroDevolucion(LocalDateTime fechaRegistroDevolucion) {
        this.fechaRegistroDevolucion = fechaRegistroDevolucion;
    }

    public LocalDateTime getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(LocalDateTime fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public String getCondicionAlDevolver() {
        return condicionAlDevolver;
    }

    public void setCondicionAlDevolver(String condicionAlDevolver) {
        this.condicionAlDevolver = condicionAlDevolver;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSolicitarInspeccion() {
        return solicitarInspeccion;
    }

    public void setSolicitarInspeccion(String solicitarInspeccion) {
        this.solicitarInspeccion = solicitarInspeccion;
    }

    public Integer getEspecialistaAsignadoId() {
        return especialistaAsignadoId;
    }

    public void setEspecialistaAsignadoId(Integer especialistaAsignadoId) {
        this.especialistaAsignadoId = especialistaAsignadoId;
    }

    public LocalDateTime getFechaInspeccionProgramada() {
        return fechaInspeccionProgramada;
    }

    public void setFechaInspeccionProgramada(LocalDateTime fechaInspeccionProgramada) {
        this.fechaInspeccionProgramada = fechaInspeccionProgramada;
    }

    public String getInspeccionRealizada() {
        return inspeccionRealizada;
    }

    public void setInspeccionRealizada(String inspeccionRealizada) {
        this.inspeccionRealizada = inspeccionRealizada;
    }

    public String getEstadoInspeccion() {
        return estadoInspeccion;
    }

    public void setEstadoInspeccion(String estadoInspeccion) {
        this.estadoInspeccion = estadoInspeccion;
    }

    public Integer getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
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

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
}
