package com.comforest.core.auth

import com.comforest.core.service.ServiceId

@JvmInline
value class UserId(val value: Long) {
    override fun toString(): String = value.toString()
}

data class AuthUser(
    val id: UserId,
    val serviceId: ServiceId,
)
