package com.foodexpress.microservice.Config;

import com.foodexpress.microservice.Security.Password;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    // Inicializar el password
    @Bean
    public Password passwordEncoder() {
        return new Password();

    }
}
