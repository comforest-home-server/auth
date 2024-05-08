package com.comforest.core.auth.token

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash(value = "access_token")
internal class AccessTokenEntity(
    token: String,
    userId: Long,
    ttl: Long,
) {
    @Id
    var token: String = token
        protected set

    var userId: Long = userId
        protected set

    @TimeToLive
    val ttl: Long = ttl
}
