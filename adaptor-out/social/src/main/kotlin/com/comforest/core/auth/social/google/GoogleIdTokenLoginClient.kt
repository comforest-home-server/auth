package com.comforest.core.auth.social.google

import com.comforest.core.SocialLoginFailedException
import com.comforest.core.auth.SocialUser
import org.springframework.stereotype.Component

@Component
internal class GoogleIdTokenLoginClient(
    private val googleIdTokenService: GoogleIdTokenService,
) {
    suspend fun login(token: String): SocialUser {
        val googleUserId = googleIdTokenService.getUserId(token) ?: throw SocialLoginFailedException()

        return GoogleSocialUser(id = googleUserId)
    }
}
