package com.app.JuberEats.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("dev") // only active in dev profile
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI defineOpenApi(){
        Server server = new Server();
        server.setUrl("http://localhost:9200");
        server.setDescription("Development");

        Contact contact = new Contact();
        contact.setName("JuberEats Admin");
        contact.setEmail("jubereats@mail.com");

        License license = new License();
        license.setName("Juber Eats License");
        license.setUrl("https://jubereats.app/api/licence");

        Info info = new Info()
                .title("Juber Eats API")
                .version("1.0")
                .description("Juber Eats API endpoints documentation")
                .license(license)
                .contact(contact);

        return new OpenAPI().info(info).servers(List.of(server));
    }

    @Bean
    public GroupedOpenApi v1Api(){
        return GroupedOpenApi
                .builder()
                .group("v1")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi v2Api(){
        return GroupedOpenApi
                .builder()
                .group("v2")
                .pathsToMatch("/api/v2/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi(){
        return GroupedOpenApi
                .builder()
                .group("Admin")
                .pathsToMatch("/api/admin/**")
                .build();
    }
}
