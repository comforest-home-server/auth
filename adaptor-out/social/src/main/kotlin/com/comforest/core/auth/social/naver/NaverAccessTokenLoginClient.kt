package com.comforest.core.auth.social.naver

import com.comforest.core.SocialLoginFailedException
import com.comforest.core.auth.SocialUser
import org.springframework.stereotype.Component

@Component
internal class NaverAccessTokenLoginClient(
    private val naverAuthClient: NaverOpenApiClient,
) {
    suspend fun login(token: String): SocialUser {
        val userResponse = naverAuthClient.getUserProfile("Bearer $token") ?: throw SocialLoginFailedException()

        if (userResponse.resultCode == "00") return userResponse.userInfo

        throw SocialLoginFailedException()
    }
}
