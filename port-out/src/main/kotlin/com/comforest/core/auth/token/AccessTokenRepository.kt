package com.comforest.core.auth.token

import com.comforest.core.auth.Token
import com.comforest.core.auth.UserId

interface AccessTokenRepository {
    suspend fun findByValue(value: String): Token?
    suspend fun save(token: Token)
    suspend fun removeByUserId(userId: UserId)
}
