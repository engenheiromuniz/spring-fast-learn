package com.mztech.ms_cliente.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI clienteOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ms-cliente API")
                        .description("Microsserviço responsável pelo cadastro e gerenciamento de clientes.")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("MZTech")
                                .email("contato@mztech.com")));
    }
}