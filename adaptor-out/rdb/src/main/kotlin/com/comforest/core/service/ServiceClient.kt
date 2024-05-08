package com.comforest.core.service

import com.comforest.core.ServiceKeyNotFoundException
import org.springframework.stereotype.Component

@Component
internal class ServiceClient(
    private val serviceKeyJpaRepository: ServiceKeyJpaRepository,
) : ServiceQueryRepository {
    override fun findByKey(key: String): Service {
        return serviceKeyJpaRepository.findByKey(key)?.service?.toDomain() ?: throw ServiceKeyNotFoundException
    }

    private fun ServiceEntity.toDomain(): Service = Service(
        id = ServiceId(this.id),
        code = this.code,
    )
}
