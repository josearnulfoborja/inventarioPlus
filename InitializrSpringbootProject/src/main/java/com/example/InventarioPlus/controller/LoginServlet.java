package com.mycompany.controller;

import com.mycompany.dao.UsuarioDAO;
import com.mycompany.model.Usuario;
// import com.mycompany.util.PasswordUtils; // TODO: Implementar cuando esté disponible
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet para manejar el proceso de autenticación de usuarios
 * Responsable de validar credenciales y establecer sesiones
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAO();
    }

    /**
     * Maneja las peticiones GET - Muestra la página de login
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Si ya hay sesión activa, redirigir al dashboard
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            response.sendRedirect("dashboard.jsp");
            return;
        }
        
        // Mostrar página de login
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Maneja las peticiones POST - Procesa el login
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        
        // Validar parámetros
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            
            request.setAttribute("error", "Por favor ingrese usuario y contraseña.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        try {
            // Intentar autenticar usuario
            Usuario usuario = usuarioDAO.autenticar(username.trim(), password);
            
            if (usuario != null) {
                // Login exitoso - Crear sesión
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario.getUsername());
                session.setAttribute("rol", usuario.getRol());
                session.setAttribute("idUsuario", usuario.getIdUsuario());
                session.setAttribute("usuarioCompleto", usuario);
                
                // Configurar tiempo de sesión
                if ("on".equals(rememberMe)) {
                    session.setMaxInactiveInterval(30 * 24 * 60 * 60); // 30 días
                } else {
                    session.setMaxInactiveInterval(2 * 60 * 60); // 2 horas
                }
                
                // Registrar en historial (si está implementado)
                registrarAccesoSistema(usuario.getIdUsuario(), request.getRemoteAddr());
                
                // Redirigir según rol
                String redirectURL = determinarURLRedirect(usuario.getRol());
                response.sendRedirect(redirectURL);
                
            } else {
                // Login fallido
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            // Error en el proceso de autenticación
            System.err.println("Error en LoginServlet: " + e.getMessage());
            e.printStackTrace();
            
            request.setAttribute("error", "Error interno del sistema. Intente nuevamente.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    /**
     * Determina la URL de redirección según el rol del usuario
     */
    private String determinarURLRedirect(String rol) {
        switch (rol.toUpperCase()) {
            case "ADMIN":
                return "dashboard.jsp";
            case "EVALUADOR":
                return "dashboard.jsp";
            case "CLIENTE":
                return "prestamos.jsp"; // Los clientes van directo a sus préstamos
            default:
                return "dashboard.jsp";
        }
    }
    
    /**
     * Registra el acceso al sistema en el historial
     */
    private void registrarAccesoSistema(int idUsuario, String ipAddress) {
        try {
            // TODO: Implementar registro en historial cuando esté disponible
            // HistorialDAO historialDAO = new HistorialDAO();
            // historialDAO.registrarAccion(idUsuario, "LOGIN", "Acceso al sistema desde IP: " + ipAddress);
        } catch (Exception e) {
            // No interrumpir el flujo si falla el registro
            System.err.println("Error registrando acceso en historial: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "LoginServlet - Maneja la autenticación de usuarios";
    }
}
