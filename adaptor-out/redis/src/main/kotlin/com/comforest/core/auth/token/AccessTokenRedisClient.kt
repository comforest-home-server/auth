package com.comforest.core.auth.token

import com.comforest.core.auth.Token
import com.comforest.core.auth.UserId
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
    override suspend fun findByValue(value: String): Token? = withContext(Dispatchers.IO) {
        repository.findByIdOrNull(value)?.toToken()
    }

    override suspend fun save(token: Token): Unit = withContext(Dispatchers.IO) {
        repository.save(token.toAccessTokenEntity())
    }

    override suspend fun removeByUserId(userId: UserId) = withContext(Dispatchers.IO) {
        repository.deleteByUserId(userId.value)
    }

    private fun AccessTokenEntity.toToken(): Token =
        Token(
            value = this.token,
            userId = UserId(this.userId),
            expiredAt = LocalDateTime.now().plusSeconds(this.ttl),
        )

    private fun Token.toAccessTokenEntity(): AccessTokenEntity =
        AccessTokenEntity(
            userId = this.userId.value,
            token = this.value,
            ttl = Duration.between(LocalDateTime.now(), this.expiredAt).seconds,
        )
}
