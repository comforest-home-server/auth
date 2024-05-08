package com.comforest.core.auth.token

import com.comforest.core.auth.TokenWithServiceId
import com.comforest.core.auth.UserId

interface AccessTokenRepository {
    suspend fun findByValue(value: String): TokenWithServiceId?
    suspend fun save(token: TokenWithServiceId)
    suspend fun removeByUserId(userId: UserId)
}
