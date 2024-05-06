package com.comforest.core.auth

interface AuthCommandRepository {
    fun registerUser(loginType: LoginType, socialId: String): AuthUser
    fun deleteUser(userId: UserId)
}
