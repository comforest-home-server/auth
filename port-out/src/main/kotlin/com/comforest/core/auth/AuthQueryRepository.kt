package com.comforest.core.auth

interface AuthQueryRepository {
    suspend fun findAuthUserList(socialType: LoginType, socialId: String): List<AuthUser>
}
