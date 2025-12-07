package com.example.InventarioPlus.service;

import com.example.InventarioPlus.model.Devolucion;
import com.example.InventarioPlus.repository.DevolucionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService {

    private final DevolucionRepository devolucionRepository;

    public DevolucionService(DevolucionRepository devolucionRepository) {
        this.devolucionRepository = devolucionRepository;
    }

    // Listar todas las devoluciones
    public List<Devolucion> listarDevoluciones() {
        return devolucionRepository.findAll();
    }

    // Obtener devolución por ID
    public Optional<Devolucion> obtenerDevolucionPorId(Integer id) {
        return devolucionRepository.findById(id);
    }

    // Guardar o actualizar devolución
    public Devolucion guardarDevolucion(Devolucion devolucion) {
        if (devolucion.getIdDevolucion() == null) {
            devolucion.setFechaCreacion(LocalDateTime.now());
        }
        devolucion.setFechaActualizacion(LocalDateTime.now());
        return devolucionRepository.save(devolucion);
    }

    // Eliminar devolución
    public void eliminarDevolucion(Integer id) {
        devolucionRepository.deleteById(id);
    }

    // Buscar devoluciones por préstamo
    public List<Devolucion> buscarPorPrestamo(Integer idPrestamo) {
        return devolucionRepository.findByIdPrestamo(idPrestamo);
    }

    // Buscar devoluciones por especialista
    public List<Devolucion> buscarPorEspecialista(Integer especialistaId) {
        return devolucionRepository.findByEspecialistaAsignadoId(especialistaId);
    }

    // Buscar devoluciones por estado de inspección
    public List<Devolucion> buscarPorEstadoInspeccion(String estado) {
        return devolucionRepository.findByEstadoInspeccion(estado);
    }

    // Buscar devoluciones que solicitan inspección
    public List<Devolucion> buscarQueSolicitanInspeccion(String solicitar) {
        return devolucionRepository.findBySolicitarInspeccion(solicitar);
    }
}
