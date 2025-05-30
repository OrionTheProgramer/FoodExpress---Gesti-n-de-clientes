package com.foodexpress.microservice.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Clientes", version = "v1", description = "Servicios para gestionar Clientes de FoodExpress"))
public class OpenApiConfig {
}
