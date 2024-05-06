package com.comforest.core.auth

import com.comforest.core.user.UserEntity
import com.comforest.core.user.UserRepository
import org.springframework.stereotype.Repository

@Repository
internal class AuthCommandRepositoryImpl(
    private val authSocialRepository: AuthSocialRepository,
    private val userRepository: UserRepository,
) : AuthCommandRepository {
    override fun registerUser(loginType: LoginType, socialId: String): AuthUser {
        val user = userRepository.save(UserEntity())
        authSocialRepository.save(AuthSocialEntity(loginType.toSocialType(), socialId, user.id))

        return AuthUser(UserId(user.id))
    }

    override fun deleteUser(userId: UserId) {
        authSocialRepository.deleteByUserId(userId.value)
    }
}
