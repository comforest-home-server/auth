package com.comforest.core.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class AuthQueryRepositoryImpl(
    private val authSocialRepository: AuthSocialRepository,
) : AuthQueryRepository {
    override suspend fun findAuthUser(id: UserId): AuthUser? = withContext(Dispatchers.IO) {
        val authSocial = authSocialRepository.findByUserId(id.value) ?: return@withContext null

        AuthUser(UserId(authSocial.userId))
    }

    override suspend fun findAuthUser(socialType: LoginType, socialId: String): AuthUser? = withContext(Dispatchers.IO) {
        val authSocial =
            authSocialRepository.findByIdOrNull(SocialInfo(socialType.toSocialType(), socialId)) ?: return@withContext null

        AuthUser(UserId(authSocial.userId))
    }
}
