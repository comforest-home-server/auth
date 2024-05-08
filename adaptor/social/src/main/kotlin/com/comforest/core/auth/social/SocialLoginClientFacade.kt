package com.comforest.core.auth.social

import com.comforest.core.auth.LoginType
import com.comforest.core.auth.SocialUser
import com.comforest.core.auth.social.apple.AppleIdTokenLoginClient
import com.comforest.core.auth.social.google.GoogleIdTokenLoginClient
import com.comforest.core.auth.social.kakao.KakaoAccessTokenLoginClient
import com.comforest.core.auth.social.naver.NaverAccessTokenLoginClient
import com.comforest.core.auth.social.test.TestLoginClient
import org.springframework.stereotype.Component

@Component
internal class SocialLoginClientFacade(
    private val naverSocialLoginClient: NaverAccessTokenLoginClient,
    private val kakaoSocialLoginClient: KakaoAccessTokenLoginClient,
    private val googleIdTokenLoginClient: GoogleIdTokenLoginClient,
    private val appleIdTokenLoginClient: AppleIdTokenLoginClient,
    private val testLoginClient: TestLoginClient,
) : SocialLoginClient {

    override suspend fun login(loginType: LoginType, token: String): SocialUser =
        when (loginType) {
            LoginType.NAVER_ACCESS_TOKEN -> naverSocialLoginClient.login(token)
            LoginType.KAKAO_ACCESS_TOKEN -> kakaoSocialLoginClient.login(token)
            LoginType.GOOGLE_ID_TOKEN -> googleIdTokenLoginClient.login(token)
            LoginType.APPLE_ID_TOKEN -> appleIdTokenLoginClient.login(token)
            LoginType.TEST -> testLoginClient.login(token)
            LoginType.REFRESH -> throw UnsupportedOperationException()
        }
}
