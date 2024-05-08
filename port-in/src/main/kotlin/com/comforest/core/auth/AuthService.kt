package com.comforest.core.auth

import com.comforest.core.service.ServiceId

interface AuthService {
    suspend fun login(serviceId: ServiceId, loginType: LoginType, token: String): AuthToken
    suspend fun getAuthUserByAccessToken(token: String): AuthUser?
}
