package aziztechs.sn.appaxiomegroupbtpback.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for OpenAPI documentation.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion BTP API")
                        .description("API pour le processus de gestion BTP")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("AXIOME GROUP BTP")
                                .email("contact@axiome-group.sn")
                                .url("https://www.axiome-group.sn"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
