package com.comforest.core.auth.social.kakao

import com.comforest.core.auth.social.kakao.dto.KakaoUserResponse
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.GetExchange

internal interface KakaoOpenApiClient {
    @GetExchange("/v2/user/me")
    suspend fun getTokenInfo(
        @RequestHeader("Authorization") authorization: String,
    ): KakaoUserResponse?
}
