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
    fun generateAccessToken(userId: UserId): Token {
        val token = accessTokenPolicy.generate(userId)
        tokenCommandRepository.saveAccessToken(token)
        return token
    }

    fun generateRefreshToken(userId: UserId): Token {
        val token = refreshTokenPolicy.generate(userId)
        tokenCommandRepository.saveRefreshToken(token)
        return token
    }

    fun renewRefreshToken(token: Token): Token {
        token.validate()
        tokenCommandRepository.removeRefreshToken(token)
        return generateRefreshToken(token.userId)
    }

    fun validateAccessToken(value: String): Token {
        accessTokenPolicy.assertValidate(value)
        return tokenQueryRepository.findAccessToken(value).validate()
    }

    fun validateRefreshToken(value: String): Token {
        refreshTokenPolicy.assertValidate(value)
        return tokenQueryRepository.findRefreshToken(value).validate()
    }
}
