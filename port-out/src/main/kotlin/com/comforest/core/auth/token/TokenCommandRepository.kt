package com.comforest.core.auth.token

import com.comforest.core.auth.Token
import com.comforest.core.auth.UserId

interface TokenCommandRepository {
    fun saveAccessToken(token: Token)
    fun saveRefreshToken(token: Token)

    fun removeRefreshToken(token: Token)

    fun deleteAllToken(userId: UserId)
}
