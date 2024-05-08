package com.comforest.core.auth

import com.comforest.core.service.ServiceId
import java.time.LocalDateTime
import java.util.Base64

interface TokenSpec {
    val value: String
    val userId: UserId
    val expiredAt: LocalDateTime

    val isExpired: Boolean
        get() = expiredAt.isBefore(LocalDateTime.now())

    fun toBinary(): ByteArray = value.toBinary()
}

data class Token(
    override val value: String,
    override val userId: UserId,
    override val expiredAt: LocalDateTime,
) : TokenSpec {
    constructor(value: ByteArray, userId: UserId, expiredAt: LocalDateTime) : this(
        Base64.getEncoder().encodeToString(value),
        userId,
        expiredAt,
    )
}

data class TokenWithServiceId(
    val token: Token,
    val serviceId: ServiceId,
) : TokenSpec by token

fun String.toBinary(): ByteArray {
    return Base64.getDecoder().decode(this)
}
