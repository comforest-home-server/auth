package com.comforest.core.auth.server.dto

import com.comforest.core.auth.AuthDetailInfo
import com.comforest.core.auth.UserId

data class PassportResponse(
    val serviceCode: String,
    val passport: PassportInfoResponse,
)

data class PassportInfoResponse(
    val userId: UserId,
)

fun AuthDetailInfo.toResponse(): PassportResponse = PassportResponse(
    serviceCode = this.service.code,
    passport = PassportInfoResponse(
        userId = this.user.id,
    ),
)
