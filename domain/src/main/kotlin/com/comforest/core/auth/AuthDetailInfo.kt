package com.comforest.core.auth

import com.comforest.core.service.ServiceId
import com.comforest.core.service.ServiceInfo
import com.comforest.core.user.User

@JvmInline
value class UserId(val value: Long) {
    override fun toString(): String = value.toString()
}

data class AuthInfo(
    val userId: UserId,
    val serviceId: ServiceId,
)

data class AuthDetailInfo(
    val user: User,
    val service: ServiceInfo,
)
