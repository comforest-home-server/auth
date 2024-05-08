package com.comforest.core.auth

import com.comforest.core.service.ServiceId
import com.comforest.core.user.UserEntity
import com.comforest.core.user.UserJpaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Repository

@Repository
internal class AuthCommandRepositoryImpl(
    private val authSocialJpaRepository: AuthSocialJpaRepository,
    private val userJpaRepository: UserJpaRepository,
) : AuthCommandRepository {
    override suspend fun registerUser(serviceId: ServiceId, loginType: LoginType, socialId: String): AuthUser = withContext(Dispatchers.IO) {
        val user = userJpaRepository.save(UserEntity(serviceId.value))
        authSocialJpaRepository.save(AuthSocialEntity(loginType.toSocialType(), socialId, user.id))

        AuthUser(UserId(user.id), serviceId)
    }

    override suspend fun deleteUser(userId: UserId) = withContext(Dispatchers.IO) {
        authSocialJpaRepository.deleteByUserId(userId.value)
    }
}
