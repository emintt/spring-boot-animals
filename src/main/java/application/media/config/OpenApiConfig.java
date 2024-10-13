package application.media.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                version = "1.0",
                title = "Spring boot media app"
        ),
        servers = @Server(
                description = "Local environment",
                url = "http://localhost:8080"
        )
)
public class OpenApiConfig {
}
