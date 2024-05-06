package com.comforest.core.auth

import com.comforest.core.user.UserEntity
import com.comforest.core.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Repository

@Repository
internal class AuthCommandRepositoryImpl(
    private val authSocialRepository: AuthSocialRepository,
    private val userRepository: UserRepository,
) : AuthCommandRepository {
    override suspend fun registerUser(loginType: LoginType, socialId: String): AuthUser = withContext(Dispatchers.IO) {
        val user = userRepository.save(UserEntity())
        authSocialRepository.save(AuthSocialEntity(loginType.toSocialType(), socialId, user.id))

        AuthUser(UserId(user.id))
    }

    override suspend fun deleteUser(userId: UserId) = withContext(Dispatchers.IO) {
        authSocialRepository.deleteByUserId(userId.value)
    }
}
