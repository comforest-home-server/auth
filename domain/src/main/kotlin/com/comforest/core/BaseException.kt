package com.comforest.core

sealed class BaseException(
    val code: String,
    val description: String,
    override val cause: Throwable? = null,
) : RuntimeException(cause)

data object InvalidTokenException : BaseException("Auth001", "유효하지 않는 토큰 입니다.")
data object ExpiredTokenException : BaseException("Auth002", "만료된 토큰 입니다.")

data object NotFoundUserException : BaseException("User001", "유저를 찾을 수 없습니다.")

class SocialLoginFailedException(cause: Throwable? = null) : BaseException("Auth003", "소셜 로그인이 실패했습니다.", cause)
