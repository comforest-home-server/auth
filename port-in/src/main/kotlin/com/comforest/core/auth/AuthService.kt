package com.comforest.core.auth

interface AuthService {
    suspend fun login(loginType: LoginType, token: String): AuthToken
    suspend fun getAuthUserByAccessToken(token: String): AuthUser?
}
