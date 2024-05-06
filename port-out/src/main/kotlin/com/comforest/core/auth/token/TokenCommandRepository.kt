package com.comforest.core.auth.token

import com.comforest.core.auth.Token
import com.comforest.core.auth.UserId

interface TokenCommandRepository {
    suspend fun saveAccessToken(token: Token)
    suspend fun saveRefreshToken(token: Token)

    suspend fun removeRefreshToken(token: Token)

    suspend fun deleteAllToken(userId: UserId)
}
