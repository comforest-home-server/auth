package com.comforest.core.auth

import com.comforest.core.ExpiredTokenException
import com.comforest.core.InvalidTokenException
import com.comforest.core.auth.token.AccessTokenRepository
import com.comforest.core.auth.token.RefreshTokenRepository
import com.comforest.core.auth.token.TokenPolicy
import com.comforest.core.service.ServiceId

internal class TokenService(
    private val accessTokenPolicy: TokenPolicy,
    private val refreshTokenPolicy: TokenPolicy,
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    suspend fun generateAccessToken(userId: UserId, serviceId: ServiceId): Token {
        val token = accessTokenPolicy.generate(userId)
        accessTokenRepository.save(TokenWithServiceId(token, serviceId))
        return token
    }

    suspend fun generateRefreshToken(userId: UserId): Token {
        val token = refreshTokenPolicy.generate(userId)
        refreshTokenRepository.save(token)
        return token
    }

    suspend fun renewRefreshToken(token: Token): Token {
        validateToken(token)
        refreshTokenRepository.remove(token)
        return generateRefreshToken(token.userId)
    }

    suspend fun validateAccessToken(value: String): TokenWithServiceId {
        accessTokenPolicy.assertValidate(value)
        val token = accessTokenRepository.findByValue(value)
        return validateToken(token)
    }

    suspend fun validateRefreshToken(value: String): Token {
        refreshTokenPolicy.assertValidate(value)
        val token = refreshTokenRepository.findByValue(value)
        return validateToken(token)
    }

    private fun <T : TokenSpec> validateToken(token: T?): T {
        if (token == null) throw InvalidTokenException
        if (token.isExpired) throw ExpiredTokenException
        return token
    }
}
