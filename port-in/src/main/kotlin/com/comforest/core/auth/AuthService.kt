package com.comforest.core.auth

interface AuthService {
    fun login(loginType: LoginType, token: String): AuthToken
    fun getAuthUserByAccessToken(token: String): AuthUser?
}
