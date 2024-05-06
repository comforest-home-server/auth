package com.comforest.core.user

import com.comforest.core.auth.UserId

interface UserCommandRepository {
    fun updateUser(user: User)
    fun deleteUser(userId: UserId)
}
