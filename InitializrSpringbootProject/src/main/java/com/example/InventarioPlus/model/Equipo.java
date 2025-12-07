package com.example.InventarioPlus.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Long id;

    @Column(length = 200)
    private String nombre;

    @Column(name = "numero_serial", length = 100)
    private String numeroSerial;

    @Column(name = "requiere_inspeccion")
    private Boolean requiereInspeccion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "costo_creacion", precision = 12, scale = 2)
    private BigDecimal costoCreacion;

    @Column(name = "fecha_dia")
    private LocalDateTime fechaDia;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_disponible")
    private LocalDateTime fechaDisponible;

    @Column(name = "fecha_uso_hora")
    private LocalDateTime fechaUsoHora;

    @Column(name = "requiere_especialista")
    private Boolean requiereEspecialista;

    @Column(name = "activo")
    private Boolean activo;

    @Column(length = 100)
    private String codigo;

    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;

    @Column(name = "numero_serie", length = 100)
    private String numeroSerie;

    @Column(name = "valor_estimado", precision = 12, scale = 2)
    private BigDecimal valorEstimado;

    @Column(name = "costo_dia", precision = 12, scale = 2)
    private BigDecimal costoDia;

    // Relaciones (pueden ser ajustadas según tus entidades)
    @ManyToOne(optional = true)
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "tipo_id")
    private TipoEquipo tipo;

    // Campos transitorios para recibir IDs en JSON
    @Transient
    @JsonProperty("modelo_id")
    private Long modeloId;

    @Transient
    @JsonProperty("tipo_id")
    private Long tipoId;

    @Transient
    @JsonProperty("estado_id")
    private Long estadoId;

    @Transient
    @JsonProperty("ubicacion_id")
    private Long ubicacionId;

    @Transient
    @JsonProperty("marca_id")
    private Long marcaId;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroSerial() {
        return numeroSerial;
    }

    public Boolean getRequiereInspeccion() {
        return requiereInspeccion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public BigDecimal getCostoCreacion() {
        return costoCreacion;
    }

    public LocalDateTime getFechaDia() {
        return fechaDia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaDisponible() {
        return fechaDisponible;
    }

    public LocalDateTime getFechaUsoHora() {
        return fechaUsoHora;
    }

    public Boolean getRequiereEspecialista() {
        return requiereEspecialista;
    }

    public Boolean getActivo() {
        return activo;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public TipoEquipo getTipo() {
        return tipo;
    }

    public EstadosEquipo getEstadoEquipo() {
        return estadoEquipo;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public Marca getMarca() {
        return marca;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "estado_id")
    private EstadosEquipo estadoEquipo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @ManyToOne(optional = true)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Equipo() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
    }

    public void setRequiereInspeccion(Boolean requiereInspeccion) {
        this.requiereInspeccion = requiereInspeccion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setCostoCreacion(BigDecimal costoCreacion) {
        this.costoCreacion = costoCreacion;
    }

    public void setFechaDia(LocalDateTime fechaDia) {
        this.fechaDia = fechaDia;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaDisponible(LocalDateTime fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
    }

    public void setFechaUsoHora(LocalDateTime fechaUsoHora) {
        this.fechaUsoHora = fechaUsoHora;
    }

    public void setRequiereEspecialista(Boolean requiereEspecialista) {
        this.requiereEspecialista = requiereEspecialista;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setTipo(TipoEquipo tipo) {
        this.tipo = tipo;
    }

    public void setEstadoEquipo(EstadosEquipo estadoEquipo) {
        this.estadoEquipo = estadoEquipo;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public BigDecimal getCostoDia() {
        return costoDia;
    }

    public void setCostoDia(BigDecimal costoDia) {
        this.costoDia = costoDia;
    }

    // Getters y setters para IDs transitorios
    @JsonIgnore
    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    @JsonIgnore
    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    @JsonIgnore
    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    @JsonIgnore
    public Long getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Long ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    @JsonIgnore
    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }
}
