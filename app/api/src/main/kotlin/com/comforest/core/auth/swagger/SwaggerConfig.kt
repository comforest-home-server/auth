package com.comforest.core.auth.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title("Comforest's Auth Api")
                    .version("1.0.0")
                    .description("Comforest 홈서버 Auth 서버"),
            )
            .servers(
                listOf(
                    Server().url("localhost"),
                ),
            )
}
