package com.comforest.core.auth

import com.comforest.core.service.ServiceId

interface AuthCommandRepository {
    suspend fun registerUser(serviceId: ServiceId, loginType: LoginType, socialId: String): UserId
    suspend fun deleteUser(userId: UserId)
}
