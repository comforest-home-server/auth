package com.comforest.core.auth

import com.comforest.core.service.ServiceId

interface AuthQueryRepository {
    suspend fun findAuthInfo(serviceId: ServiceId, socialType: LoginType, socialId: String): AuthInfo?
}
