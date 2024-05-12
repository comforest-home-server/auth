package com.comforest.core

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration
class CacheConfig {

    @Bean
    fun localCacheManager(): CacheManager {
        val cacheList = CacheType.entries
            .map {
                CaffeineCache(
                    it.cacheName,
                    Caffeine.newBuilder()
                        .maximumSize(200)
                        .expireAfterWrite(it.duration)
                        .build(),
                )
            }

        val cacheManager = SimpleCacheManager()
        cacheManager.setCaches(cacheList)
        return cacheManager
    }
}
