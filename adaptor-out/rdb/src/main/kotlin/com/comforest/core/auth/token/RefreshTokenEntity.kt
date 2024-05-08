package com.comforest.core.auth.token

import com.comforest.core.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "auth_refresh_token")
internal class RefreshTokenEntity(
    userId: Long,
    refreshToken: ByteArray,
    expiredAt: LocalDateTime,
) : AuditingEntity() {
    @Id
    @Column(name = "refresh_token_id")
    val id: Long = 0

    @Column(name = "user_id")
    val userId: Long = userId

    @Column(name = "refresh_token", columnDefinition = "BINARY(45)")
    val refreshToken: ByteArray = refreshToken

    @Column(name = "expired_at")
    val expiredAt: LocalDateTime = expiredAt
}
