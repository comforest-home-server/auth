package com.comforest.core.auth.social.kakao.dto

import com.comforest.core.auth.SocialUser

internal data class KakaoUserResponse(
    override val id: String,
//    @JsonProperty("kakao_account") val kakaoAccount: KakaoAccount, 동의 항목 필요
) : SocialUser
