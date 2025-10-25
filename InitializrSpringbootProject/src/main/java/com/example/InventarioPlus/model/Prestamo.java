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

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrestamo;

    private LocalDate fechaEntrega;
    private LocalDate fechaDevolucion;
    private LocalDate fechaPrevista;
    private BigDecimal costoTotal;
    private String estadoPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "id_especialista")
    private Especialista especialista;

    public Prestamo() {}

    public Prestamo(LocalDate fechaEntrega, LocalDate fechaDevolucion, LocalDate fechaPrevista,
                    BigDecimal costoTotal, String estadoPrestamo, Cliente cliente,
                    Equipo equipo, Especialista especialista) {
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaPrevista = fechaPrevista;
        this.costoTotal = costoTotal;
        this.estadoPrestamo = estadoPrestamo;
        this.cliente = cliente;
        this.equipo = equipo;
        this.especialista = especialista;
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
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

    public Cliente getCliente() {
        return cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Especialista getEspecialista() {
        return especialista;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setEspecialista(Especialista especialista) {
        this.especialista = especialista;
    }

    
}
