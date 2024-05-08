package com.comforest.core.auth.social.apple.key

import org.springframework.web.service.annotation.GetExchange

interface AppleApiClient {
    @GetExchange("https://appleid.apple.com/auth/keys")
    suspend fun getPublicKeys(): ApplePublicKeysResponse?
}
