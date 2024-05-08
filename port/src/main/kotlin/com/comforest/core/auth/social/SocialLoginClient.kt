package com.comforest.core.auth.social

import com.comforest.core.auth.LoginType
import com.comforest.core.auth.SocialUser

interface SocialLoginClient {
    suspend fun login(loginType: LoginType, token: String): SocialUser
}
