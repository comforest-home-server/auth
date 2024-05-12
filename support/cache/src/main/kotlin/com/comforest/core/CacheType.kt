package com.comforest.core

import java.time.Duration

enum class CacheType(val cacheName: String, val duration: Duration) {
    User("user", Duration.ofMinutes(3))
}
