/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.InventarioPlus.model;

// siguiendo el patrón de 'Modelo' usamos campos FK (ids) en lugar de relaciones @ManyToOne

/**
 *
 * @author User
 */

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrestamo;

    private LocalDate fechaEntrega;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private LocalDate fechaPrevista;
    private BigDecimal costoTotal;
    private String estadoPrestamo;

    @Column(name = "id_cliente")
    private Long clienteId;

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @Column(name = "id_equipo")
    private Long equipoId;

    @ManyToOne
    @JoinColumn(name = "id_equipo", insertable = false, updatable = false)
    private Equipo equipo;

    @Column(name = "id_especialista")
    private Long especialistaId;

    public Prestamo() {
    }

    // CONSTRUCTOR PARA INICIALIZAR
    public Prestamo(LocalDate fechaEntrega, LocalDate fechaPrestamo, LocalDate fechaDevolucion, LocalDate fechaPrevista,
            BigDecimal costoTotal, String estadoPrestamo, Long clienteId,
            Long equipoId, Long especialistaId) {
        this.fechaEntrega = fechaEntrega;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaPrevista = fechaPrevista;
        this.costoTotal = costoTotal;
        this.estadoPrestamo = estadoPrestamo;
        this.clienteId = clienteId;
        this.equipoId = equipoId;
        this.especialistaId = especialistaId;
    }

    // METODOS SET Y GETTERS
    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public LocalDate getFechaPrevista() {
        return fechaPrevista;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getEquipoId() {
        return equipoId;
    }

    public Long getEspecialistaId() {
        return especialistaId;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setFechaPrevista(LocalDate fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setEquipoId(Long equipoId) {
        this.equipoId = equipoId;
    }

    public void setEspecialistaId(Long especialistaId) {
        this.especialistaId = especialistaId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}
