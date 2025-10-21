package com.example.InventarioPlus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuración global de CORS para permitir peticiones desde el frontend
 * Angular.
 * 
 * Esta configuración permite que el frontend (http://localhost:4200)
 * pueda comunicarse con el backend Spring Boot sin errores de CORS.
 * 
 * @author User
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permite enviar credenciales (cookies, authorization headers)
        config.setAllowCredentials(true);

        // Permite peticiones desde el frontend Angular
        config.addAllowedOrigin("http://localhost:4200");

        // Permite todos los headers
        config.addAllowedHeader("*");

        // Permite todos los métodos HTTP (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod("*");

        // Aplica esta configuración a todas las rutas que empiecen con /api/
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}
