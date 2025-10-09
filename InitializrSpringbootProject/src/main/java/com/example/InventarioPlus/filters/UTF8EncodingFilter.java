package com.example.InventarioPlus.filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro para asegurar que todas las peticiones y respuestas 
 * usen codificación UTF-8 para el manejo correcto de caracteres especiales.
 * 
 * Este filtro se ejecuta antes de cualquier servlet/JSP y configura:
 * - Request encoding: Para datos enviados al servidor
 * - Response encoding: Para datos enviados al cliente
 * - Content type: Para especificar el tipo de contenido y codificación
 */
@WebFilter(filterName = "UTF8EncodingFilter", urlPatterns = {"/*"})
public class UTF8EncodingFilter implements Filter {
    
    private static final String UTF8_ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización del filtro - se ejecuta una vez al inicio
        System.out.println("UTF8EncodingFilter inicializado - Codificación: " + UTF8_ENCODING);
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Cast a tipos específicos de HTTP
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Configurar codificación para la petición (datos del cliente al servidor)
        if (httpRequest.getCharacterEncoding() == null) {
            httpRequest.setCharacterEncoding(UTF8_ENCODING);
        }
        
        // Configurar codificación para la respuesta (datos del servidor al cliente)
        httpResponse.setCharacterEncoding(UTF8_ENCODING);
        
        // Configurar el Content-Type por defecto para respuestas HTML
        if (httpResponse.getContentType() == null) {
            httpResponse.setContentType(CONTENT_TYPE);
        }
        
        // Agregar header adicional para asegurar UTF-8
        httpResponse.setHeader("Content-Type", CONTENT_TYPE);
        
        // Continuar con la cadena de filtros
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // Limpieza del filtro - se ejecuta al finalizar la aplicación
        System.out.println("UTF8EncodingFilter destruído");
    }
}