package com.gradProj.HUrry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CORS_Config {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:8080");
        corsConfig.addAllowedOrigin("http://localhost:8081" +
                "");  // Frontend URL (if running locally)

        corsConfig.setAllowedOrigins(List.of("*")); // Allow ngrok's domain
        corsConfig.setAllowedMethods(List.of("*"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowCredentials(true);  // Allow credentials if needed

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Apply to all endpoints





        return new CorsFilter(source);
    }
}

//        corsConfig.addAllowedOrigin("https://15c7-91-186-249-30.ngrok-free.app");  // Your ngrok public URL
//        corsConfig.addAllowedMethod("*");  // Allow all HTTP methods (GET, POST, etc.)
//        corsConfig.addAllowedHeader("*");  // Allow all headers
