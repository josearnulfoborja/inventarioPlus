package com.example.InventarioPlus.model;

public class Equipo {

    private int idEquipo;
    private String nombre;
    private String descripcion;
    private String estado;
    private double costoDia;
    private double costoHora;
    private boolean requiereEspecialista;

    // Constructor vacío
    public Equipo() {
    }

    // Constructor con parámetros
    public Equipo(int idEquipo, String nombre, String descripcion, String estado, double costoDia, double costoHora, boolean requiereEspecialista) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.costoDia = costoDia;
        this.costoHora = costoHora;
        this.requiereEspecialista = requiereEspecialista;
    }

    // Getters y Setters
    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getCostoDia() {
        return costoDia;
    }

    public void setCostoDia(double costoDia) {
        this.costoDia = costoDia;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }

    public boolean isRequiereEspecialista() {
        return requiereEspecialista;
    }

    public void setRequiereEspecialista(boolean requiereEspecialista) {
        this.requiereEspecialista = requiereEspecialista;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", costoDia=" + costoDia +
                ", costoHora=" + costoHora +
                ", requiereEspecialista=" + requiereEspecialista +
                '}';
    }
}