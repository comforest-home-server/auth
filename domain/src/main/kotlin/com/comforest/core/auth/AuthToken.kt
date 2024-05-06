package com.comforest.core.auth

data class AuthToken(
    val accessToken: Token,
    val refreshToken: Token? = null,
)
