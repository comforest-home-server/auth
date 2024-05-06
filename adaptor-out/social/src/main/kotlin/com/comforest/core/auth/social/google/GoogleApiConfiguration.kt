package com.comforest.core.auth.social.google

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(GoogleApiProperties::class)
class GoogleApiConfiguration
