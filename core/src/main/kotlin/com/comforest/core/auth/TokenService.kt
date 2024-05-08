package com.comforest.core.auth

import com.comforest.core.auth.token.AccessTokenRepository
import com.comforest.core.auth.token.RefreshTokenRepository
import com.comforest.core.auth.token.TokenPolicy

internal class TokenService(
    private val accessTokenPolicy: TokenPolicy,
    private val refreshTokenPolicy: TokenPolicy,
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    suspend fun generateAccessToken(userId: UserId): Token {
        val token = accessTokenPolicy.generate(userId)
        accessTokenRepository.save(token)
        return token
    }

    suspend fun generateRefreshToken(userId: UserId): Token {
        val token = refreshTokenPolicy.generate(userId)
        refreshTokenRepository.save(token)
        return token
    }

    suspend fun renewRefreshToken(token: Token): Token {
        token.validate()
        refreshTokenRepository.remove(token)
        return generateRefreshToken(token.userId)
    }

    suspend fun validateAccessToken(value: String): Token {
        accessTokenPolicy.assertValidate(value)
        return accessTokenRepository.findByValue(value).validate()
    }

    suspend fun validateRefreshToken(value: String): Token {
        refreshTokenPolicy.assertValidate(value)
        return refreshTokenRepository.findByValue(value).validate()
    }
}
