package com.comforest.core.auth.token

import com.comforest.core.auth.Token
import com.comforest.core.auth.UserId
import com.comforest.core.auth.toBinary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
internal class RefreshTokenClient(
    private val repository: RefreshTokenJpaRepository,
) : RefreshTokenRepository {
    override suspend fun findByValue(value: String): Token? = withContext(Dispatchers.IO) {
        val token = repository.findByValidToken(value.toBinary())
        token?.toToken()
    }

    override suspend fun save(token: Token): Unit = withContext(Dispatchers.IO) {
        repository.save(token.toRefreshTokenEntity())
    }

    override suspend fun remove(token: Token): Unit = withContext(Dispatchers.IO) {
        repository.deleteByRefreshToken(token.toBinary())
    }

    override suspend fun removeByUserId(userId: UserId) = withContext(Dispatchers.IO) {
        repository.deleteByUserId(userId.value)
    }

    private fun RefreshTokenEntity.toToken(): Token {
        return Token(
            value = this.refreshToken,
            userId = UserId(this.userId),
            expiredAt = this.expiredAt,
        )
    }

    private fun Token.toRefreshTokenEntity(): RefreshTokenEntity =
        RefreshTokenEntity(
            userId = this.userId.value,
            refreshToken = this.toBinary(),
            expiredAt = this.expiredAt,
        )
}
