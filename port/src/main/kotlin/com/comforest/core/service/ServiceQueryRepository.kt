package com.comforest.core.service

interface ServiceQueryRepository {
    fun findByKey(key: String): ServiceInfo
    fun findById(serviceId: ServiceId): ServiceInfo
}
