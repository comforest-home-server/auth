package com.comforest.core.auth.server

import com.comforest.core.InvalidAuthorizationException
import com.comforest.core.auth.AuthUseCase
import com.comforest.core.auth.server.dto.PassportResponse
import com.comforest.core.auth.server.dto.toResponse
import com.comforest.core.auth.swagger.ApiTag
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@Tag(name = ApiTag.SERVER)
@RestController
class ServerController(
    private val authUseCase: AuthUseCase,
) {

    @Operation(summary = "패스포트 가져오기")
    @GetMapping("/api/v1/passport")
    suspend fun getPassport(
        @RequestHeader(name = "Authorization") auth: String,
    ): PassportResponse {
        if (auth.lowercase().startsWith("bearer ")) {
            val token = auth.substring(7)

            val authInfo = authUseCase.getAuthDetailInfoByAccessToken(token) ?: throw InvalidAuthorizationException
            return authInfo.toResponse()
        }

        throw InvalidAuthorizationException
    }
}
