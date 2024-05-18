package com.comforest.core.service

import com.comforest.core.ServiceIdNotFoundException
import com.comforest.core.ServiceKeyNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class ServiceClient(
    private val serviceJpaRepository: ServiceJpaRepository,
    private val serviceKeyJpaRepository: ServiceKeyJpaRepository,
) : ServiceQueryRepository {
    override fun findByKey(key: String): ServiceInfo {
        return serviceKeyJpaRepository.findByKey(key)?.service?.toDomain() ?: throw ServiceKeyNotFoundException
    }

    override fun findById(serviceId: ServiceId): ServiceInfo {
        return serviceJpaRepository.findByIdOrNull(serviceId.value)?.toDomain() ?: throw ServiceIdNotFoundException
    }

    private fun ServiceEntity.toDomain(): ServiceInfo = ServiceInfo(
        id = ServiceId(this.id),
        code = this.code,
    )
}
