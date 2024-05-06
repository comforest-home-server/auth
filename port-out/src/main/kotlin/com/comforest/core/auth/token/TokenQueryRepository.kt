package com.comforest.core.auth.token

import com.comforest.core.auth.Token

interface TokenQueryRepository {
    fun findAccessToken(value: String): Token?
    fun findRefreshToken(token: String): Token?
}
