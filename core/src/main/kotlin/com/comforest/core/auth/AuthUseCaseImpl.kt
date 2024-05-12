package com.comforest.core.auth

import com.comforest.core.BaseException
import com.comforest.core.auth.social.SocialLoginClient
import com.comforest.core.service.ServiceId
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
internal class AuthUseCaseImpl(
    private val socialAuthClient: SocialLoginClient,
    private val authCommandRepository: AuthCommandRepository,
    private val authQueryRepository: AuthQueryRepository,
    private val tokenService: TokenService,
) : AuthUseCase {

    @Transactional
    override suspend fun login(serviceId: ServiceId, loginType: LoginType, token: String): AuthToken {
        return if (loginType == LoginType.REFRESH) {
            loginByRefreshToken(token, serviceId)
        } else {
            socialLogin(serviceId, loginType, token)
        }
    }

    @Transactional
    override suspend fun getAuthUserByAccessToken(token: String): AuthUser? {
        return try {
            val accessToken = tokenService.validateAccessToken(token)
            AuthUser(accessToken.userId, ServiceId(0))
        } catch (e: BaseException) {
            null
        }
    }

    private suspend fun socialLogin(serviceId: ServiceId, loginType: LoginType, token: String): AuthToken {
        val socialUser = socialAuthClient.login(loginType, token)

        val authUser = getOrCreateAuthUser(serviceId, loginType, socialUser.id)



        return AuthToken(
            accessToken = tokenService.generateAccessToken(authUser.id, serviceId),
            refreshToken = tokenService.generateRefreshToken(authUser.id),
        )
    }

    private suspend fun loginByRefreshToken(tokenValue: String, serviceId: ServiceId): AuthToken {
        val token = tokenService.validateRefreshToken(tokenValue)

        return AuthToken(
            accessToken = tokenService.generateAccessToken(token.userId, serviceId),
            refreshToken = tokenService.renewRefreshToken(token),
        )
    }

    private suspend fun getOrCreateAuthUser(serviceId: ServiceId, loginType: LoginType, socialUserId: String): AuthUser {
        val authUser = authQueryRepository.findAuthUserList(loginType, socialUserId)
            .firstOrNull { it.serviceId == serviceId }

        if (authUser != null) return authUser

        return authCommandRepository.registerUser(serviceId, loginType, socialUserId)
    }
}
