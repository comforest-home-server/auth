package com.comforest.core.auth.social.google

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("social.google")
data class GoogleApiProperties(
    val clientId: String,
)
