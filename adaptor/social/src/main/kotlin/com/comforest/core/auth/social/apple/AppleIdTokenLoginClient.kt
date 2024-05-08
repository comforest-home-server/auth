package com.comforest.core.auth.social.apple

import com.comforest.core.SocialLoginFailedException
import com.comforest.core.auth.SocialUser
import com.comforest.core.auth.social.apple.key.AppleApiClient
import com.comforest.core.jwt.JwtUtils
import com.comforest.core.jwt.key.JwtKey
import org.springframework.stereotype.Component

@Component
class AppleIdTokenLoginClient(
    private val jwtUtils: JwtUtils,
    private val appleApiClient: AppleApiClient,
) {
    suspend fun login(token: String): SocialUser {
        val publicKeys = appleApiClient.getPublicKeys() ?: throw SocialLoginFailedException()
        val header = jwtUtils.getHeader(token)
        val kid = header["kid"] ?: throw SocialLoginFailedException()
        val alg = header["alg"] ?: throw SocialLoginFailedException()

        val key = publicKeys[kid, alg]?.toJwtKey() ?: throw SocialLoginFailedException()

        val payload = validateAndGetPayload(token, key) ?: throw SocialLoginFailedException()

        return AppleSocialUser(payload.sub)
    }

    private fun validateAndGetPayload(token: String, key: JwtKey): AppleIdTokenPayload? {
        if (jwtUtils.validate(token, key).not()) return null

        val payload = jwtUtils.getPayload(token, AppleIdTokenPayload::class.java)

        if (payload.iss != APPLE_ISS || payload.aud != APPLE_AUD) return null

        return payload
    }

    companion object {
        private const val APPLE_ISS = "https://appleid.apple.com"
        private const val APPLE_AUD = "onboard.onboard-iOS-App"
    }
}
