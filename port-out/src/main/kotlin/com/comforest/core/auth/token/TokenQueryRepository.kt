package com.comforest.core.auth.token

import com.comforest.core.auth.Token

interface TokenQueryRepository {
    suspend fun findAccessToken(value: String): Token?
    suspend fun findRefreshToken(token: String): Token?
}
