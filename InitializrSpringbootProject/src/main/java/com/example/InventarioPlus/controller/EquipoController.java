package com.example.InventarioPlus.controller;


import com.example.InventarioPlus.model.*;
import com.example.InventarioPlus.controller.*;
import com.example.InventarioPlus.repository.*;
import com.example.InventarioPlus.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
private final EquipoService equipoService;

    // Inyección de dependencias por constructor
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // GET: listar todos los equipos
    @GetMapping
    public ResponseEntity<List<Equipo>> listarEquipos() {
        List<Equipo> equipos = equipoService.listarEquipos();
        return ResponseEntity.ok(equipos);
    }

    // GET: buscar equipo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipo(@PathVariable Long id) {
        Optional<Equipo> equipo = equipoService.obtenerEquipoPorId(id);
        return equipo.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // GET: buscar por número de serie
    @GetMapping("/serial/{numeroSerial}")
    public ResponseEntity<Equipo> obtenerEquipoPorSerial(@PathVariable String numeroSerial) {
        Optional<Equipo> equipo = equipoService.buscarPorNumeroSerial(numeroSerial);
        return equipo.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // GET: buscar por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Equipo>> obtenerEquiposPorEstado(@PathVariable String estado) {
        List<Equipo> equipos = equipoService.buscarPorEstado(estado);
        return ResponseEntity.ok(equipos);
    }

    // GET: buscar por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Equipo>> obtenerEquiposPorTipo(@PathVariable String tipo) {
        List<Equipo> equipos = equipoService.buscarPorTipo(tipo);
        return ResponseEntity.ok(equipos);
    }

    // GET: buscar por ubicación
    @GetMapping("/ubicacion/{ubicacion}")
    public ResponseEntity<List<Equipo>> obtenerEquiposPorUbicacion(@PathVariable String ubicacion) {
        List<Equipo> equipos = equipoService.buscarPorUbicacion(ubicacion);
        return ResponseEntity.ok(equipos);
    }

    // GET: buscar por nombre (contiene texto)
    @GetMapping("/buscar")
    public ResponseEntity<List<Equipo>> buscarEquiposPorNombre(@RequestParam String nombre) {
        List<Equipo> equipos = equipoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(equipos);
    }

    // GET: equipos que requieren inspección
    @GetMapping("/inspeccion/{requiere}")
    public ResponseEntity<List<Equipo>> obtenerEquiposPorInspeccion(@PathVariable String requiere) {
        List<Equipo> equipos = equipoService.buscarEquiposQueRequierenInspeccion(requiere);
        return ResponseEntity.ok(equipos);
    }

    // GET: búsqueda avanzada con múltiples criterios
    @GetMapping("/busqueda-avanzada")
    public ResponseEntity<List<Equipo>> busquedaAvanzada(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String ubicacion) {
        
        List<Equipo> equipos = equipoService.buscarPorCriteriosMultiples(nombre, tipo, estado, ubicacion);
        return ResponseEntity.ok(equipos);
    }

    // POST: crear nuevo equipo
    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        try {
            // Verificar si el número de serie ya existe
            if (equipo.getNumeroSerial() != null && 
                equipoService.existeNumeroSerial(equipo.getNumeroSerial())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
            }
            
            Equipo equipoGuardado = equipoService.guardarEquipo(equipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PUT: actualizar equipo completo
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        try {
            Equipo equipoActualizado = equipoService.actualizarEquipo(id, equipo);
            return ResponseEntity.ok(equipoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PATCH: actualizar estado del equipo
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Equipo> actualizarEstadoEquipo(@PathVariable Long id, @RequestParam String estado) {
        Optional<Equipo> equipoOpt = equipoService.obtenerEquipoPorId(id);
        if (equipoOpt.isPresent()) {
            Equipo equipo = equipoOpt.get();
            equipo.setEstado(estado);
            Equipo equipoActualizado = equipoService.guardarEquipo(equipo);
            return ResponseEntity.ok(equipoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH: actualizar ubicación del equipo
    @PatchMapping("/{id}/ubicacion")
    public ResponseEntity<Equipo> actualizarUbicacionEquipo(@PathVariable Long id, @RequestParam String ubicacion) {
        Optional<Equipo> equipoOpt = equipoService.obtenerEquipoPorId(id);
        if (equipoOpt.isPresent()) {
            Equipo equipo = equipoOpt.get();
            equipo.setUbicacion(ubicacion);
            Equipo equipoActualizado = equipoService.guardarEquipo(equipo);
            return ResponseEntity.ok(equipoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE: eliminar equipo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEquipo(@PathVariable Long id) {
        try {
            if (equipoService.obtenerEquipoPorId(id).isPresent()) {
                equipoService.eliminarEquipo(id);
                return ResponseEntity.ok("Equipo eliminado con éxito.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body("Error al eliminar el equipo.");
        }
    }

    // GET: estadísticas - contar equipos por estado
    @GetMapping("/estadisticas/estado/{estado}")
    public ResponseEntity<Long> contarEquiposPorEstado(@PathVariable String estado) {
        long count = equipoService.contarPorEstado(estado);
        return ResponseEntity.ok(count);
    }
}