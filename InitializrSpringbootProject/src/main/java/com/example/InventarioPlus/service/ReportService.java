package com.example.InventarioPlus.service;

import com.example.InventarioPlus.model.Prestamo;
import com.example.InventarioPlus.repository.PrestamoRepository;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import com.example.InventarioPlus.repository.EquipoRepository;
import com.example.InventarioPlus.repository.EvaluacionTecnicaRepository;
import com.example.InventarioPlus.repository.HistorialRepository;
import com.example.InventarioPlus.repository.UsuarioRepository;
import com.example.InventarioPlus.repository.DevolucionRepository;
import com.example.InventarioPlus.repository.EspecialistaRepository;

@Service
public class ReportService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EvaluacionTecnicaRepository evaluacionTecnicaRepository;

    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private EspecialistaRepository especialistaRepository;

    /**
     * Genera el reporte por nombre y formato. Busca primero un .jasper en classpath
     * (resources/reports), si no existe intenta compilar el .jrxml.
     *
     * @param reportName nombre base del reporte (p.ej. "prestamos")
     * @param format     "pdf" o "xlsx"
     * @param parameters parámetros opcionales para el reporte
     * @return bytes del archivo generado
     * @throws Exception on failure
     */
    public byte[] generateReport(String reportName, String format, Map<String, Object> parameters) throws Exception {
        if (parameters == null) {
            parameters = new HashMap<>();
        }

        // Decide datasource based on report name. Aquí añadimos casos concretos.
        JRBeanCollectionDataSource dataSource;
        switch (reportName.toLowerCase()) {
            case "prestamos": {
                List<Prestamo> items = prestamoRepository.findAll();
                dataSource = new JRBeanCollectionDataSource(items);
                break;
            }
            case "equipos": {
                List<com.example.InventarioPlus.model.Equipo> items = equipoRepository.findAll();
                dataSource = new JRBeanCollectionDataSource(items);
                break;
            }
            case "inspecciones":
            case "evaluaciones": {
                List<com.example.InventarioPlus.model.EvaluacionTecnica> items = evaluacionTecnicaRepository.findAll();
                dataSource = new JRBeanCollectionDataSource(items);
                break;
            }
            case "mantenimiento":
            case "historial": {
                // Transform Historial into DTOs with usuarioNombre to avoid printing object
                // refs in JRXML
                List<com.example.InventarioPlus.model.Historial> rawHist = historialRepository.findAll();
                java.util.List<Object> dtoList = new java.util.ArrayList<>();
                for (com.example.InventarioPlus.model.Historial h : rawHist) {
                    String usuarioNombre = null;
                    if (h.getUsuario() != null) {
                        usuarioNombre = (h.getUsuario().getNombre() == null ? "" : h.getUsuario().getNombre()) +
                                (h.getUsuario().getApellido() == null ? "" : " " + h.getUsuario().getApellido());
                    }
                    java.util.Map<String, Object> map = new java.util.HashMap<>();
                    map.put("idHistorial", h.getIdHistorial());
                    map.put("fechaAccion", h.getFechaAccion());
                    map.put("textoAccion", h.getTextoAccion());
                    map.put("usuarioNombre", usuarioNombre);
                    dtoList.add(map);
                }
                dataSource = new JRBeanCollectionDataSource(dtoList);
                break;
            }
            case "actividad_usuario":
            case "actividad-usuario":
            case "actividad": {
                // Para actividad por usuario usamos el historial y mostramos el usuario en cada
                // fila
                List<com.example.InventarioPlus.model.Historial> rawHist = historialRepository.findAll();
                java.util.List<Object> dtoList = new java.util.ArrayList<>();
                for (com.example.InventarioPlus.model.Historial h : rawHist) {
                    String usuarioNombre = null;
                    if (h.getUsuario() != null) {
                        usuarioNombre = (h.getUsuario().getNombre() == null ? "" : h.getUsuario().getNombre()) +
                                (h.getUsuario().getApellido() == null ? "" : " " + h.getUsuario().getApellido());
                    }
                    java.util.Map<String, Object> map = new java.util.HashMap<>();
                    map.put("idHistorial", h.getIdHistorial());
                    map.put("fechaAccion", h.getFechaAccion());
                    map.put("textoAccion", h.getTextoAccion());
                    map.put("usuarioNombre", usuarioNombre);
                    dtoList.add(map);
                }
                dataSource = new JRBeanCollectionDataSource(dtoList);
                break;
            }
            case "devoluciones": {
                List<com.example.InventarioPlus.model.Devolucion> items = devolucionRepository.findAll();

                // Pre-cargar especialistas en un mapa id->nombre para evitar consultas por fila
                Map<Long, String> especialistas = new HashMap<>();
                for (com.example.InventarioPlus.model.Especialista e : especialistaRepository.findAll()) {
                    if (e.getId() != null) {
                        especialistas.put(e.getId(), e.getNombre());
                    }
                }

                // Armar DTOs planos con valores listos para el JRXML
                java.util.List<Map<String, Object>> dtoList = new java.util.ArrayList<>();
                for (com.example.InventarioPlus.model.Devolucion d : items) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("idDevolucion", d.getIdDevolucion());
                    map.put("idPrestamo", d.getIdPrestamo());
                    map.put("fechaRegistroDevolucion", d.getFechaRegistroDevolucion());
                    map.put("fechaDevolucionReal", d.getFechaDevolucionReal());
                    map.put("condicionAlDevolver", d.getCondicionAlDevolver());
                    map.put("observaciones", d.getObservaciones());

                    // Convertir 0/1/null a "Si"/"No"
                    String insp = d.getSolicitarInspeccion();
                    boolean isYes = insp != null && !insp.trim().isEmpty() && !"0".equalsIgnoreCase(insp.trim())
                            && !"false".equalsIgnoreCase(insp.trim());
                    map.put("inspeccionTexto", isYes ? "Si" : "No");

                    map.put("especialistaAsignadoId", d.getEspecialistaAsignadoId());
                    if (d.getEspecialistaAsignadoId() != null) {
                        map.put("especialistaNombre", especialistas.get(d.getEspecialistaAsignadoId().longValue()));
                    } else {
                        map.put("especialistaNombre", null);
                    }

                    map.put("estadoInspeccion", d.getEstadoInspeccion());
                    map.put("inspeccionRealizada", d.getInspeccionRealizada());

                    dtoList.add(map);
                }

                dataSource = new JRBeanCollectionDataSource(dtoList);
                break;
            }
            default: {
                // por defecto, dataset vacío
                dataSource = new JRBeanCollectionDataSource(java.util.Collections.emptyList());
                break;
            }
        }

        // Cargar .jasper o compilar .jrxml
        String basePath = "reports/" + reportName;
        ClassPathResource jasperResource = new ClassPathResource(basePath + ".jasper");
        JasperReport jasperReport;
        if (jasperResource.exists()) {
            try (InputStream is = jasperResource.getInputStream()) {
                jasperReport = (JasperReport) JRLoader.loadObject(is);
            }
        } else {
            // compilar jrxml
            ClassPathResource jrxml = new ClassPathResource(basePath + ".jrxml");
            if (!jrxml.exists()) {
                throw new IllegalArgumentException("No se encontró el reporte: " + basePath + "(.jasper|.jrxml)");
            }
            try (InputStream is = jrxml.getInputStream()) {
                jasperReport = JasperCompileManager.compileReport(is);
            }
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if ("pdf".equalsIgnoreCase(format)) {
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } else if ("xlsx".equalsIgnoreCase(format) || "xls".equalsIgnoreCase(format)) {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setOnePagePerSheet(false);
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                return baos.toByteArray();
            }
        } else {
            throw new IllegalArgumentException("Formato no soportado: " + format);
        }
    }
}
