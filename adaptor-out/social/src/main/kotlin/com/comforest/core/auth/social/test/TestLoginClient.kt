package com.comforest.core.auth.social.test

import com.comforest.core.SocialLoginFailedException
import com.comforest.core.auth.SocialUser

internal interface TestLoginClient {
    fun login(token: String): SocialUser
}

internal class SandboxTestLoginClient : TestLoginClient{
    override fun login(token: String): SocialUser {
        if(token.startsWith("test").not()) throw SocialLoginFailedException()

        return TestSocialUser(token)
    }
}

internal class ProdcutionTestLoginClient : TestLoginClient{
    override fun login(token: String): SocialUser {
        throw UnsupportedOperationException()
    }
}
