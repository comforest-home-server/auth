package com.comforest.core.auth

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

internal interface AuthSocialJpaRepository : JpaRepository<AuthSocialEntity, Long> {
    @Query(
        "SELECT e from AuthSocialEntity e " +
            "JOIN FETCH e.user " +
            "WHERE e.socialType = :socialType and e.socialId = :socialId ",
    )
    fun findBySocialTypeAndSocialId(socialType: SocialType, socialId: String): List<AuthSocialEntity>
    fun findByUserId(userId: Long): AuthSocialEntity?
    fun deleteByUserId(userId: Long)
}
