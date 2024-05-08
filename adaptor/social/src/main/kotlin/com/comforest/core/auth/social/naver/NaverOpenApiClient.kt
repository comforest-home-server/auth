package com.comforest.core.auth.social.naver

import com.comforest.core.auth.social.naver.dto.NaverUserResponse
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.GetExchange

internal interface NaverOpenApiClient {
    @GetExchange("/v1/nid/me")
    suspend fun getUserProfile(
        @RequestHeader("Authorization") authorization: String,
    ): NaverUserResponse?
}
