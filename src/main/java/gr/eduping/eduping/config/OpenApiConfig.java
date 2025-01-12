package gr.eduping.eduping.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Edu-Ping API",
                version = "1.0.0",
                description = "API for users and announcements"
        )
)
@Configuration
public class OpenApiConfig {
}
