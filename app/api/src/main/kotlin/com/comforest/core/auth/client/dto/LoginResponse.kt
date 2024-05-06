package com.comforest.core.auth.client.dto

import com.comforest.core.auth.AuthToken
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginResponse(
    @Schema(required = true)
    val accessToken: String,
    @Schema(required = false)
    val refreshToken: String?,
)

fun AuthToken.toResponse(): LoginResponse = LoginResponse(
    accessToken = this.accessToken.value,
    refreshToken = this.refreshToken?.value,
)
