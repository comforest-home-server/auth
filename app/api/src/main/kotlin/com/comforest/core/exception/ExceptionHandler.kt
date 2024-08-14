package com.comforest.core.exception

import com.comforest.core.AuthenticationException
import com.comforest.core.CustomException
import com.comforest.core.NotFoundUserException
import com.comforest.core.ServiceIdNotFoundException
import com.comforest.core.ServiceKeyNotFoundException
import com.comforest.core.SocialLoginFailedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(AuthenticationException::class)
    fun exceptionHandler(ex: AuthenticationException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity
            .status(401)
            .body(ex.toResponse())
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(ex: Exception): ResponseEntity<ExceptionResponse> {
        return ResponseEntity
            .status(401)
            .body(ExceptionResponse("Unknown", "알 수 없는 에러 입니다.", ex.message))
    }
}

data class ExceptionResponse(
    val code: String,
    val messageForUser: String,
    val messageForDev: String?,
)

private fun CustomException.toResponse(): ExceptionResponse = ExceptionResponse(
    this.code,
    this.toMessageForUser(),
    this.message,
)

private fun CustomException.toMessageForUser(): String = when (this) {
    is AuthenticationException -> "유효하지 않은 인증입니다."
    NotFoundUserException -> TODO()
    ServiceIdNotFoundException -> TODO()
    ServiceKeyNotFoundException -> TODO()
    is SocialLoginFailedException -> TODO()
}
