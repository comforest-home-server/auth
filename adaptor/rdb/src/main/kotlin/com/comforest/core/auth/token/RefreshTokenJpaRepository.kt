package com.comforest.core.auth.token

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

internal interface RefreshTokenJpaRepository : JpaRepository<RefreshTokenEntity, Long> {
    @Query("select e from RefreshTokenEntity e where e.refreshToken = :refreshToken and e.expiredAt > now()")
    fun findByValidToken(refreshToken: ByteArray): RefreshTokenEntity?
    fun deleteByRefreshToken(refreshToken: ByteArray)
    fun deleteByUserId(userId: Long)
}
