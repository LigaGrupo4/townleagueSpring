package com.sanvalero.townleague.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIDocConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Townleague API")
                        .description("API REST de la liga")
                        .contact(new Contact()
                                .name("Grupo 4")
                                .email("a24898@svalero.com")
                                .url("https://github.com/LigaGrupo4/townleagueSpring"))
                        .version("1.0"));
    }
}
