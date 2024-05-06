package com.comforest.core.auth.token

import com.comforest.core.InvalidTokenException
import com.comforest.core.auth.Token
import com.comforest.core.auth.UserId
import java.time.LocalDateTime

internal interface TokenUtils {
    fun generate(userId: UserId, expiredAt: LocalDateTime): Token
    fun validate(token: String): Boolean
    fun getUserId(token: String): Long?
    fun assertValidate(token: String) {
        if (validate(token).not()) throw InvalidTokenException
    }
}
