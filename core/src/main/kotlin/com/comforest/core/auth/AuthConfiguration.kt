package com.comforest.core.auth

import com.comforest.core.auth.token.AccessTokenRepository
import com.comforest.core.auth.token.OpaqueTokenUtils
import com.comforest.core.auth.token.RefreshTokenRepository
import com.comforest.core.auth.token.TokenPolicy
import java.time.Duration
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthConfiguration {

    @Value("\${auth.access-token.expire-duration}")
    lateinit var accessTokenExpireDuration: Duration

    @Value("\${auth.refresh-token.expire-duration}")
    lateinit var refreshTokenExpireDuration: Duration

    @Bean
    internal fun accessTokenPolicy(): TokenPolicy = TokenPolicy(
        OpaqueTokenUtils(40),
        accessTokenExpireDuration,
    )

    @Bean
    internal fun refreshTokenPolicy(): TokenPolicy = TokenPolicy(
        OpaqueTokenUtils(60),
        refreshTokenExpireDuration,
    )

    @Bean
    internal fun tokenService(
        accessTokenRepository: AccessTokenRepository,
        refreshTokenRepository: RefreshTokenRepository,
    ): TokenService =
        TokenService(
            accessTokenPolicy(),
            refreshTokenPolicy(),
            accessTokenRepository,
            refreshTokenRepository,
        )
}
