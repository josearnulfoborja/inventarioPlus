package com.example.InventarioPlus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;

    // Constructor vacío (obligatorio para JPA)
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
