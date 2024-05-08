package com.comforest.core.auth.social.test

import com.comforest.core.auth.SocialUser

data class TestSocialUser(
    override val id: String,
) : SocialUser
