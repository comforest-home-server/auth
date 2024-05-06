package com.comforest.core.auth

import com.comforest.core.BaseException
import com.comforest.core.auth.social.SocialLoginClient
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
internal class AuthServiceImpl(
    private val socialAuthClient: SocialLoginClient,
    private val authCommandRepository: AuthCommandRepository,
    private val authQueryRepository: AuthQueryRepository,
    private val tokenService: TokenService,
) : AuthService {

    @Transactional
    override fun login(loginType: LoginType, token: String): AuthToken {
        return if (loginType == LoginType.REFRESH) {
            loginByRefreshToken(token)
        } else {
            socialLogin(loginType, token)
        }
    }

    @Transactional
    override fun getAuthUserByAccessToken(token: String): AuthUser? {
        return try {
            val accessToken = tokenService.validateAccessToken(token)
            AuthUser(accessToken.userId)
        } catch (e: BaseException) {
            null
        }
    }

    private fun socialLogin(loginType: LoginType, token: String): AuthToken {
        val socialUser = socialAuthClient.login(loginType, token)

        val authUser = getOrCreateAuthUser(loginType, socialUser.id)

        return AuthToken(
            accessToken = tokenService.generateAccessToken(authUser.id),
            refreshToken = tokenService.generateRefreshToken(authUser.id),
        )
    }

    private fun loginByRefreshToken(tokenValue: String): AuthToken {
        val token = tokenService.validateRefreshToken(tokenValue)

        return AuthToken(
            accessToken = tokenService.generateAccessToken(token.userId),
            refreshToken = tokenService.renewRefreshToken(token),
        )
    }

    private fun getOrCreateAuthUser(loginType: LoginType, socialUserId: String): AuthUser {
        val authUser = authQueryRepository.findAuthUser(loginType, socialUserId)
        if (authUser != null) return authUser

        return authCommandRepository.registerUser(loginType, socialUserId)
    }
}
