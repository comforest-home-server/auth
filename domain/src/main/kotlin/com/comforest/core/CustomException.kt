package com.comforest.core

sealed class CustomException(val code: String, override val message: String, throwable: Throwable? = null) : RuntimeException(message, throwable)

sealed class AuthenticationException(code: String, message: String, throwable: Throwable? = null) : CustomException(code, message, throwable)
data object InvalidTokenException : AuthenticationException("Auth001", "유효하지 않는 토큰 입니다.") { private fun readResolve(): Any = InvalidTokenException }
data object ExpiredTokenException : AuthenticationException("Auth002", "만료된 토큰 입니다.") { private fun readResolve(): Any = ExpiredTokenException }
data object InvalidAuthorizationException : AuthenticationException("Auth003", "잘못된 요청") { private fun readResolve(): Any = InvalidAuthorizationException }

sealed class ClientException(code: String, message: String, throwable: Throwable? = null) : CustomException(code, message, throwable)
data object NotFoundUserException : ClientException("User001", "유저를 찾을 수 없습니다.") { private fun readResolve(): Any = NotFoundUserException }
data object ServiceKeyNotFoundException : ClientException("Service001", "잘못된 서버 키입니다.") { private fun readResolve(): Any = ServiceKeyNotFoundException }
data object ServiceIdNotFoundException : ClientException("Service002", "잘못된 서버 키입니다.") { private fun readResolve(): Any = ServiceIdNotFoundException }
class SocialLoginFailedException(cause: Throwable? = null) : ClientException("Auth003", "소셜 로그인이 실패했습니다.", cause)
