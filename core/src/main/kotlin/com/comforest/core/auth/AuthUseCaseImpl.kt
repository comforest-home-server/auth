package com.comforest.core.auth

import com.comforest.core.BaseException
import com.comforest.core.auth.social.SocialLoginClient
import com.comforest.core.service.ServiceId
import com.comforest.core.service.ServiceQueryRepository
import com.comforest.core.user.UserQueryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
internal class AuthUseCaseImpl(
    private val socialAuthClient: SocialLoginClient,
    private val authCommandRepository: AuthCommandRepository,
    private val authQueryRepository: AuthQueryRepository,
    private val userQueryRepository: UserQueryRepository,
    private val serviceInfoQueryRepository: ServiceQueryRepository,
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
    override suspend fun getAuthDetailInfoByAccessToken(token: String): AuthDetailInfo? {
        return try {
            val accessToken = tokenService.validateAccessToken(token)
            val user = userQueryRepository.getUser(accessToken.userId) ?: return null
            val serviceInfo = serviceInfoQueryRepository.findById(accessToken.serviceId)
            AuthDetailInfo(user, serviceInfo)
        } catch (e: BaseException) {
            null
        }
    }

    private suspend fun socialLogin(serviceId: ServiceId, loginType: LoginType, token: String): AuthToken {
        val socialUser = socialAuthClient.login(loginType, token)

        val userId = getOrCreateUser(serviceId, loginType, socialUser.id)

        return AuthToken(
            accessToken = tokenService.generateAccessToken(userId, serviceId),
            refreshToken = tokenService.generateRefreshToken(userId),
        )
    }

    private suspend fun loginByRefreshToken(tokenValue: String, serviceId: ServiceId): AuthToken {
        val token = tokenService.validateRefreshToken(tokenValue)

        return AuthToken(
            accessToken = tokenService.generateAccessToken(token.userId, serviceId),
            refreshToken = tokenService.renewRefreshToken(token),
        )
    }

    private suspend fun getOrCreateUser(serviceId: ServiceId, loginType: LoginType, socialUserId: String): UserId {
        val authInfo = authQueryRepository.findAuthInfo(serviceId, loginType, socialUserId)

        if (authInfo != null) return authInfo.userId

        return authCommandRepository.registerUser(serviceId, loginType, socialUserId)
    }
}
