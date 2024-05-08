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
    var id: Long = 0
        protected set

    @Column(name = "user_id")
    var userId: Long = userId
        protected set

    @Column(name = "refresh_token", columnDefinition = "BINARY(45)")
    var refreshToken: ByteArray = refreshToken
        protected set

    @Column(name = "expired_at")
    var expiredAt: LocalDateTime = expiredAt
        protected set
}
