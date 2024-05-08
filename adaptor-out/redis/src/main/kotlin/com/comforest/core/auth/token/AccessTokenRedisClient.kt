package com.comforest.core.auth.token

import com.comforest.core.auth.Token
import com.comforest.core.auth.TokenWithServiceId
import com.comforest.core.auth.UserId
import com.comforest.core.service.ServiceId
import java.time.Duration
import java.time.LocalDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class AccessTokenRedisClient(
    private val repository: AccessTokenRedisRepository,
) : AccessTokenRepository {
    override suspend fun findByValue(value: String): TokenWithServiceId? = withContext(Dispatchers.IO) {
        repository.findByIdOrNull(value)?.toToken()
    }

    override suspend fun save(token: TokenWithServiceId): Unit = withContext(Dispatchers.IO) {
        repository.save(token.toAccessTokenEntity())
    }

    override suspend fun removeByUserId(userId: UserId) = withContext(Dispatchers.IO) {
        repository.deleteByUserId(userId.value)
    }

    private fun AccessTokenEntity.toToken(): TokenWithServiceId =
        TokenWithServiceId(
            token = Token(
                value = this.token,
                userId = UserId(this.userId),
                expiredAt = LocalDateTime.now().plusSeconds(this.ttl),
            ),
            serviceId = ServiceId(this.serviceId),
        )

    private fun TokenWithServiceId.toAccessTokenEntity(): AccessTokenEntity =
        AccessTokenEntity(
            token = this.value,
            userId = this.userId.value,
            serviceId = this.serviceId.value,
            ttl = Duration.between(LocalDateTime.now(), this.expiredAt).seconds,
        )
}
