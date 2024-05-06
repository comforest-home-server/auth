package com.comforest.core.user

import com.comforest.core.auth.UserId

interface UserCommandRepository {
    suspend fun updateUser(user: User)
    suspend fun deleteUser(userId: UserId)
}
