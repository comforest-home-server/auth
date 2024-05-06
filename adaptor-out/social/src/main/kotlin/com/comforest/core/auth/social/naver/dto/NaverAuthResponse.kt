package com.comforest.core.auth.social.naver.dto

import com.comforest.core.auth.SocialUser
import com.fasterxml.jackson.annotation.JsonProperty

internal data class NaverUserResponse(
    @JsonProperty("resultcode") val resultCode: String,
    val message: String,
    @JsonProperty("response") val userInfo: NaverUserInfoResponse,
)

internal class NaverUserInfoResponse(
    override val id: String,
//    네이버 콘솔에서 추가 필요
//    val nickname: String,
//    val email: String,
) : SocialUser
