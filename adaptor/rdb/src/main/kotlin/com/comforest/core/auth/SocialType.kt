package com.comforest.core.auth

import java.lang.UnsupportedOperationException

enum class SocialType {
    KAKAO,
    NAVER,
    GOOGLE,
    APPLE,
    TEST,
}

fun LoginType.toSocialType(): SocialType =
    when (this) {
        LoginType.KAKAO_ACCESS_TOKEN -> SocialType.KAKAO
        LoginType.NAVER_ACCESS_TOKEN -> SocialType.NAVER
        LoginType.GOOGLE_ID_TOKEN -> SocialType.GOOGLE
        LoginType.APPLE_ID_TOKEN -> SocialType.APPLE
        LoginType.TEST -> SocialType.TEST
        LoginType.REFRESH -> throw UnsupportedOperationException()
    }
