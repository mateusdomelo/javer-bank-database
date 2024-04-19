package io.github.mateusdomelo.javerdb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Generated
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Javer Bank | Database Service")
                .description(
                        "Serviço de leitura, escrita, atualização, exclusão com banco de dados em memória local (H2)"
                ).contact(contactInfo());
    }

    private Contact contactInfo() {
        return new Contact()
                .name("Mateus Melo")
                .email("mateusmelo@gmail.com")
                .url("https://github.com/mateusdomelo");
    }
}
