package com.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration con = new CorsConfiguration();
        con.addAllowedOrigin("*");
        con.addAllowedHeader("*");
        con.addAllowedMethod("*");
        source.registerCorsConfiguration("/**",con);
        return new CorsFilter(source);
    }
}

