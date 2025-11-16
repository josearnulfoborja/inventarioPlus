package com.example.InventarioPlus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

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

        // Permite los headers que normalmente usa el frontend, incluyendo Authorization
        config.setAllowedHeaders(
                Arrays.asList("Authorization", "Content-Type", "Accept", "X-Requested-With", "Cache-Control"));

        // Expone la cabecera Authorization al cliente (si usas tokens en header)
        config.setExposedHeaders(List.of("Authorization"));

        // Permite métodos comunes y OPTIONS para preflight
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));

        // Tiempo en segundos que el resultado del preflight puede ser cacheado por el
        // navegador
        config.setMaxAge(3600L);

        // Permite orígenes (usa allowed origin patterns si necesitas wildcard)
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));

        // Aplica esta configuración a todas las rutas (incluye endpoints protegidos por
        // Spring Security)
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
