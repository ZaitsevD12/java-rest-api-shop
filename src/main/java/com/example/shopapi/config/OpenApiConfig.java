package com.example.shopapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development Server");

        return new OpenAPI()
                .info(new Info().title("Shop API").version("1.0").description("Shop API for household appliances"))
                .addServersItem(server);
    }
}