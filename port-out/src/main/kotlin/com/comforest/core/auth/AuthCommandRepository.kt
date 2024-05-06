package com.comforest.core.auth

interface AuthCommandRepository {
    suspend fun registerUser(loginType: LoginType, socialId: String): AuthUser
    suspend fun deleteUser(userId: UserId)
}
