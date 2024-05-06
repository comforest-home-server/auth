package com.comforest.core.user

import com.comforest.core.auth.UserId

data class User(
    val id: UserId,
    val nickname: String?,
)
