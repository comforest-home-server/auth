package com.comforest.core.auth

interface AuthQueryRepository {
    suspend fun findAuthUser(id: UserId): AuthUser?
    suspend fun findAuthUser(socialType: LoginType, socialId: String): AuthUser?
}
