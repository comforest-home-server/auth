package com.comforest.core.auth

import com.comforest.core.auth.token.TokenCommandRepository
import com.comforest.core.auth.token.TokenPolicy
import com.comforest.core.auth.token.TokenQueryRepository

internal class TokenService(
    private val accessTokenPolicy: TokenPolicy,
    private val refreshTokenPolicy: TokenPolicy,
    private val tokenQueryRepository: TokenQueryRepository,
    private val tokenCommandRepository: TokenCommandRepository,
) {
    suspend fun generateAccessToken(userId: UserId): Token {
        val token = accessTokenPolicy.generate(userId)
        tokenCommandRepository.saveAccessToken(token)
        return token
    }

    suspend fun generateRefreshToken(userId: UserId): Token {
        val token = refreshTokenPolicy.generate(userId)
        tokenCommandRepository.saveRefreshToken(token)
        return token
    }

    suspend fun renewRefreshToken(token: Token): Token {
        token.validate()
        tokenCommandRepository.removeRefreshToken(token)
        return generateRefreshToken(token.userId)
    }

    suspend fun validateAccessToken(value: String): Token {
        accessTokenPolicy.assertValidate(value)
        return tokenQueryRepository.findAccessToken(value).validate()
    }

    suspend fun validateRefreshToken(value: String): Token {
        refreshTokenPolicy.assertValidate(value)
        return tokenQueryRepository.findRefreshToken(value).validate()
    }
}
