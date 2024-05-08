package com.comforest.core.service

interface ServiceQueryRepository {
    fun findByKey(key: String): Service
}
