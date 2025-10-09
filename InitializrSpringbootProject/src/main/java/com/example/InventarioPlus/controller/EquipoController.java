package com.example.InventarioPlus.controller;

import com.example.InventarioPlus.model.Equipo;
import com.example.InventarioPlus.model.service.EquipoService;
import com.example.InventarioPlus.model.util.Conexion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/equipos")
public class EquipoController extends HttpServlet {

    private EquipoService equipoService;

    @Override
    public void init() throws ServletException {
        Connection connection = Conexion.getConnection();
        equipoService = new EquipoService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Equipo> equipos = equipoService.getAllEquipos();
            request.setAttribute("equipos", equipos);
            request.getRequestDispatcher("/equipos.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al obtener equipos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String estado = request.getParameter("estado");
            double costoDia = Double.parseDouble(request.getParameter("costoDia"));
            double costoHora = Double.parseDouble(request.getParameter("costoHora"));
            boolean requiereEspecialista = request.getParameter("requiereEspecialista") != null;

            Equipo equipo = new Equipo();
            equipo.setNombre(nombre);
            equipo.setDescripcion(descripcion);
            equipo.setEstado(estado);
            equipo.setCostoDia(costoDia);
            equipo.setCostoHora(costoHora);
            equipo.setRequiereEspecialista(requiereEspecialista);

            equipoService.addEquipo(equipo);
            response.sendRedirect(request.getContextPath() + "/equipos");
        } catch (Exception e) {
            throw new ServletException("Error al agregar equipo", e);
        }
    }
}
