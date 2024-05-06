package com.comforest.core.auth

interface AuthQueryRepository {
    fun findAuthUser(id: UserId): AuthUser?
    fun findAuthUser(socialType: LoginType, socialId: String): AuthUser?
}
