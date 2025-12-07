package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.model.Devolucion;
import com.example.InventarioPlus.service.DevolucionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {

    private final DevolucionService devolucionService;

    public DevolucionController(DevolucionService devolucionService) {
        this.devolucionService = devolucionService;
    }

    @GetMapping
    public List<Devolucion> listarDevoluciones() {
        return devolucionService.listarDevoluciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> obtenerDevolucion(@PathVariable Integer id) {
        Optional<Devolucion> devolucion = devolucionService.obtenerDevolucionPorId(id);
        return devolucion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Devolucion> crearDevolucion(@RequestBody Devolucion devolucion) {
        Devolucion nuevaDevolucion = devolucionService.guardarDevolucion(devolucion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDevolucion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> actualizarDevolucion(@PathVariable Integer id,
            @RequestBody Devolucion devolucion) {
        return devolucionService.obtenerDevolucionPorId(id).map(d -> {
            d.setIdPrestamo(devolucion.getIdPrestamo());
            d.setFechaRegistroDevolucion(devolucion.getFechaRegistroDevolucion());
            d.setFechaDevolucionReal(devolucion.getFechaDevolucionReal());
            d.setCondicionAlDevolver(devolucion.getCondicionAlDevolver());
            d.setObservaciones(devolucion.getObservaciones());
            d.setSolicitarInspeccion(devolucion.getSolicitarInspeccion());
            d.setEspecialistaAsignadoId(devolucion.getEspecialistaAsignadoId());
            d.setFechaInspeccionProgramada(devolucion.getFechaInspeccionProgramada());
            d.setInspeccionRealizada(devolucion.getInspeccionRealizada());
            d.setEstadoInspeccion(devolucion.getEstadoInspeccion());
            d.setCreadoPor(devolucion.getCreadoPor());
            Devolucion actualizada = devolucionService.guardarDevolucion(d);
            return ResponseEntity.ok(actualizada);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDevolucion(@PathVariable Integer id) {
        if (devolucionService.obtenerDevolucionPorId(id).isPresent()) {
            devolucionService.eliminarDevolucion(id);
            return ResponseEntity.ok("Devolución eliminada con éxito.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/prestamo/{idPrestamo}")
    public List<Devolucion> buscarPorPrestamo(@PathVariable Integer idPrestamo) {
        return devolucionService.buscarPorPrestamo(idPrestamo);
    }

    @GetMapping("/especialista/{especialistaId}")
    public List<Devolucion> buscarPorEspecialista(@PathVariable Integer especialistaId) {
        return devolucionService.buscarPorEspecialista(especialistaId);
    }

    @GetMapping("/estado-inspeccion/{estado}")
    public List<Devolucion> buscarPorEstadoInspeccion(@PathVariable String estado) {
        return devolucionService.buscarPorEstadoInspeccion(estado);
    }

    @GetMapping("/solicitan-inspeccion/{solicitar}")
    public List<Devolucion> buscarQueSolicitanInspeccion(@PathVariable String solicitar) {
        return devolucionService.buscarQueSolicitanInspeccion(solicitar);
    }
}
