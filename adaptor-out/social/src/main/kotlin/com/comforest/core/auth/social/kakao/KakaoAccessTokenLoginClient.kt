package com.comforest.core.auth.social.kakao

import com.comforest.core.SocialLoginFailedException
import com.comforest.core.auth.SocialUser
import org.springframework.stereotype.Component

@Component
internal class KakaoAccessTokenLoginClient(
    private val kakaoAuthClient: KakaoOpenApiClient,
) {
    suspend fun login(token: String): SocialUser =
        kakaoAuthClient.getTokenInfo("Bearer $token") ?: throw SocialLoginFailedException()
}
