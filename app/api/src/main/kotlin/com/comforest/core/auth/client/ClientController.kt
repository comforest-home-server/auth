package com.comforest.core.auth.client

import com.comforest.core.auth.AuthService
import com.comforest.core.auth.client.dto.LoginRequest
import com.comforest.core.auth.client.dto.LoginResponse
import com.comforest.core.auth.client.dto.toResponse
import com.comforest.core.auth.swagger.ApiTag
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = ApiTag.CLIENT)
@RestController
class ClientController(
    private val authService: AuthService,
) {

    @Operation(summary = "로그인")
    @PostMapping("/api/v1/login")
    suspend fun login(
        @RequestBody request: LoginRequest,
    ): LoginResponse {
        return authService.login(request.type, request.token).toResponse()
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/api/v1/logout")
    suspend fun logout() {
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/api/v1/withdraw")
    suspend fun withdraw() {
    }
}
