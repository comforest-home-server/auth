package com.comforest.core.auth

import com.comforest.core.service.ServiceId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Repository

@Repository
internal class AuthQueryRepositoryImpl(
    private val authSocialJpaRepository: AuthSocialJpaRepository,
) : AuthQueryRepository {

    override suspend fun findAuthInfo(serviceId: ServiceId, socialType: LoginType, socialId: String): AuthInfo? = withContext(Dispatchers.IO) {
        authSocialJpaRepository.findBySocialTypeAndSocialId(socialType.toSocialType(), socialId)
            .firstOrNull { it.user.serviceId == serviceId.value }
            ?.toDomain()
    }

    private fun AuthSocialEntity.toDomain(): AuthInfo = AuthInfo(
        userId = UserId(this.user.id),
        serviceId = ServiceId(this.user.serviceId),
    )
}
