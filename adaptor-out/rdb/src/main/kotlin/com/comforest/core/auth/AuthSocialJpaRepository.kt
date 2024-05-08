package com.comforest.core.auth

import org.springframework.data.jpa.repository.JpaRepository

internal interface AuthSocialJpaRepository : JpaRepository<AuthSocialEntity, SocialInfo> {
    fun findByUserId(userId: Long): AuthSocialEntity?

    fun deleteByUserId(userId: Long)
}
