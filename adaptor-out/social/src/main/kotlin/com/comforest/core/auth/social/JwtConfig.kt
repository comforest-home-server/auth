package com.comforest.core.auth.social

import com.comforest.core.jwt.JwtUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig {
    @Bean
    fun jwtUtils(objectMapper: ObjectMapper): JwtUtils = JwtUtils(objectMapper)
}
