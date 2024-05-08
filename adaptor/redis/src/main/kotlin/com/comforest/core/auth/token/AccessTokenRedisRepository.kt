package com.comforest.core.auth.token

import org.springframework.data.repository.CrudRepository

internal interface AccessTokenRedisRepository : CrudRepository<AccessTokenEntity, String> {
    fun deleteByUserId(userId: Long)
}
