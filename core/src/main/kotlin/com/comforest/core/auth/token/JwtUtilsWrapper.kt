package com.comforest.core.auth.token

import com.comforest.core.auth.Token
import com.comforest.core.auth.UserId
import com.comforest.core.jwt.JwtUtils
import com.comforest.core.jwt.key.JwtKey
import java.time.LocalDateTime

internal class JwtUtilsWrapper(
    secretKey: ByteArray,
    private val jwtUtils: JwtUtils,
) : TokenUtils {
    private val jwtKey = JwtKey(secretKey)

    override fun generate(userId: UserId, expiredAt: LocalDateTime): Token {
        val token = jwtUtils.generate(userId.toString(), expiredAt, jwtKey)
        return Token(token, userId, expiredAt)
    }

    override fun validate(token: String): Boolean =
        jwtUtils.validate(token, jwtKey)

    override fun getUserId(token: String): Long =
        jwtUtils.getUserId(token, jwtKey).toLong()
}
