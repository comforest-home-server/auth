package com.comforest.core.user

import com.comforest.core.auth.UserId

interface UserQueryRepository {
    suspend fun getUser(userId: UserId): User?
}
