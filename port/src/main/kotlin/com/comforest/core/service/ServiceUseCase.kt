package com.comforest.core.service

interface ServiceUseCase {
    fun findServiceByKey(key: String): ServiceInfo
}
