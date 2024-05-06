package com.comforest.core.auth.token.jwt.key

import io.jsonwebtoken.security.Keys
import java.security.Key

internal class JwtByteArrayKey(
    private val secretKey: ByteArray,
) : JwtKey {
    override fun getKey(): Key = Keys.hmacShaKeyFor(secretKey)
}
