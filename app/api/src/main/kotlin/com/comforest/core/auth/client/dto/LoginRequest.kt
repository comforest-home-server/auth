package com.comforest.core.auth.client.dto

import com.comforest.core.auth.LoginType
import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest(
    @Schema(title = "로그인 방식")
    val type: LoginType,
    @Schema(title = "토큰")
    val token: String,
)
