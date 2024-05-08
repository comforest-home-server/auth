package com.comforest.core.service

import org.springframework.stereotype.Component

@Component
internal class ServiceUseCaseImpl(
    private val serviceQueryRepository: ServiceQueryRepository,
) : ServiceUseCase {
    override fun findServiceByKey(key: String): Service {
        return serviceQueryRepository.findByKey(key)
    }
}
