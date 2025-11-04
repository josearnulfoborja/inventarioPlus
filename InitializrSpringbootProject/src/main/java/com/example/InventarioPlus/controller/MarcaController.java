package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.model.Marca;
import com.example.InventarioPlus.service.MarcaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public List<Marca> listarMarcas() {
        return marcaService.listarMarcas();
    }

    @GetMapping("/{id}")
    public Optional<Marca> obtenerMarca(@PathVariable Integer id) {
        return marcaService.obtenerMarcaPorId(id);
    }

    @PostMapping
    public Marca crearMarca(@RequestBody Marca marca) {
        return marcaService.guardarMarca(marca);
    }

    @PutMapping("/{id}")
    public Marca actualizarMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        return marcaService.obtenerMarcaPorId(id).map(m -> {
            m.setNombre(marca.getNombre());
            m.setActivo(marca.getActivo());
            m.setUpdatedAt(marca.getUpdatedAt());
            return marcaService.guardarMarca(m);
        }).orElseThrow(() -> new RuntimeException("Marca no encontrada con id " + id));
    }

    @DeleteMapping("/{id}")
    public String eliminarMarca(@PathVariable Integer id) {
        marcaService.eliminarMarca(id);
        return "Marca eliminada con éxito.";
    }
}
