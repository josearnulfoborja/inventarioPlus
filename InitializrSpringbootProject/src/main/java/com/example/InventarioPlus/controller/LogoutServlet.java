package com.mycompany.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet para manejar el cierre de sesión
 * Limpia la sesión y redirige al login
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogout(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogout(request, response);
    }
    
    /**
     * Procesa el cierre de sesión
     */
    private void processLogout(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Obtener sesión actual
            HttpSession session = request.getSession(false);
            
            if (session != null) {
                // Registrar cierre de sesión en historial si está disponible
                Object idUsuario = session.getAttribute("idUsuario");
                if (idUsuario != null) {
                    registrarCierreSesion((Integer) idUsuario, request.getRemoteAddr());
                }
                
                // Invalidar sesión
                session.invalidate();
            }
            
            // Redirigir al login con mensaje
            response.sendRedirect("login.jsp?mensaje=Sesión cerrada correctamente");
            
        } catch (Exception e) {
            System.err.println("Error en LogoutServlet: " + e.getMessage());
            e.printStackTrace();
            
            // En caso de error, redirigir al login de todas formas
            response.sendRedirect("login.jsp");
        }
    }
    
    /**
     * Registra el cierre de sesión en el historial
     */
    private void registrarCierreSesion(int idUsuario, String ipAddress) {
        try {
            // TODO: Implementar registro en historial cuando esté disponible
            // HistorialDAO historialDAO = new HistorialDAO();
            // historialDAO.registrarAccion(idUsuario, "LOGOUT", "Cierre de sesión desde IP: " + ipAddress);
        } catch (Exception e) {
            // No interrumpir el flujo si falla el registro
            System.err.println("Error registrando cierre de sesión en historial: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "LogoutServlet - Maneja el cierre de sesión de usuarios";
    }
}
