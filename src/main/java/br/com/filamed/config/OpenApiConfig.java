package br.com.filamed.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI filamedOpenAPI() {

        return new OpenAPI()

                .info(
                        new Info()

                                .title("FilaMed API")

                                .description("""
                                        API responsável pelo gerenciamento
                                        de pacientes, médicos, filas de atendimento
                                        e consultas médicas.
                                        """)

                                .version("1.0.0")

                                .contact(
                                        new Contact()
                                                .name("Golden Goblins Entertainment")
                                                .email("contato@filamed.com")
                                )

                                .license(
                                        new License()
                                                .name("Uso interno")
                                )
                )

                .externalDocs(
                        new ExternalDocumentation()
                                .description("Documentação do Projeto")
                );

    }

}