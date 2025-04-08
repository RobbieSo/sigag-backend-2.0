package com.example.GymAdmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Especificar los dominios permitidos
        config.setAllowedOrigins(List.of("http://localhost:5173","http://localhost:4200", "https://app-testing-deploy.web.app", "https://ares-gym-app.web.app", "https://ares-gym-site.web.app"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // Permitir credenciales

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
