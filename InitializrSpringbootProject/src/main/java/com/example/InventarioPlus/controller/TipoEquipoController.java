package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.model.TipoEquipo;
import com.example.InventarioPlus.service.TipoEquipoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos_equipo")
public class TipoEquipoController {

    private final TipoEquipoService tipoEquipoService;

    public TipoEquipoController(TipoEquipoService tipoEquipoService) {
        this.tipoEquipoService = tipoEquipoService;
    }

    @GetMapping
    public List<TipoEquipo> listarTipos() {
        return tipoEquipoService.listarTipos();
    }

    @GetMapping("/{id}")
    public Optional<TipoEquipo> obtenerTipo(@PathVariable Integer id) {
        return tipoEquipoService.obtenerTipoPorId(id);
    }

    @PostMapping
    public TipoEquipo crearTipo(@RequestBody TipoEquipo tipo) {
        return tipoEquipoService.guardarTipo(tipo);
    }

    @PutMapping("/{id}")
    public TipoEquipo actualizarTipo(@PathVariable Integer id, @RequestBody TipoEquipo tipo) {
        return tipoEquipoService.obtenerTipoPorId(id).map(t -> {
            t.setCodigo(tipo.getCodigo());
            t.setNombre(tipo.getNombre());
            t.setDescripcion(tipo.getDescripcion());
            t.setActivo(tipo.getActivo());
            t.setUpdatedAt(tipo.getUpdatedAt());
            return tipoEquipoService.guardarTipo(t);
        }).orElseThrow(() -> new RuntimeException("Tipo no encontrado con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarTipo(@PathVariable Integer id) {
        tipoEquipoService.eliminarTipo(id);
        return "Tipo de equipo eliminado con éxito.";
    }
}
