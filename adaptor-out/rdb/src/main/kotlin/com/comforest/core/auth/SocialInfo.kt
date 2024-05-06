package com.comforest.core.auth

import java.io.Serializable

data class SocialInfo(
    val socialType: SocialType,
    val socialId: String,
) : Serializable {
    constructor() : this(SocialType.KAKAO, "")
}
