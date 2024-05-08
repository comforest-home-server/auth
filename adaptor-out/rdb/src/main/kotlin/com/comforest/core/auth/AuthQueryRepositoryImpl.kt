package com.comforest.core.auth

import com.comforest.core.service.ServiceId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Repository

@Repository
internal class AuthQueryRepositoryImpl(
    private val authSocialJpaRepository: AuthSocialJpaRepository,
) : AuthQueryRepository {

    override suspend fun findAuthUserList(socialType: LoginType, socialId: String): List<AuthUser> = withContext(Dispatchers.IO) {
        authSocialJpaRepository.findBySocialTypeAndSocialId(socialType.toSocialType(), socialId)
            .map { it.toDomain() }
    }

    private fun AuthSocialEntity.toDomain(): AuthUser = AuthUser(
        id = UserId(this.user.id),
        serviceId = ServiceId(this.user.serviceId),
    )
}
