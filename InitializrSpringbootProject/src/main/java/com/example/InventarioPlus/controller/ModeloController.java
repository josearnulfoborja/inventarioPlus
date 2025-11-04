package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.model.Modelo;
import com.example.InventarioPlus.service.ModeloService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

    private final ModeloService modeloService;

    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping
    public List<Modelo> listarModelos() {
        return modeloService.listarModelos();
    }

    @GetMapping("/{id}")
    public Optional<Modelo> obtenerModelo(@PathVariable Integer id) {
        return modeloService.obtenerModeloPorId(id);
    }

    @PostMapping
    public Modelo crearModelo(@RequestBody Modelo modelo) {
        return modeloService.guardarModelo(modelo);
    }

    @PutMapping("/{id}")
    public Modelo actualizarModelo(@PathVariable Integer id, @RequestBody Modelo modelo) {
        return modeloService.obtenerModeloPorId(id).map(m -> {
            m.setMarcaId(modelo.getMarcaId());
            m.setNombre(modelo.getNombre());
            m.setCodigoModelo(modelo.getCodigoModelo());
            m.setDescripcion(modelo.getDescripcion());
            m.setActivo(modelo.getActivo());
            m.setUpdatedAt(modelo.getUpdatedAt());
            return modeloService.guardarModelo(m);
        }).orElseThrow(() -> new RuntimeException("Modelo no encontrado con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarModelo(@PathVariable Integer id) {
        modeloService.eliminarModelo(id);
        return "Modelo eliminado con éxito.";
    }
}
