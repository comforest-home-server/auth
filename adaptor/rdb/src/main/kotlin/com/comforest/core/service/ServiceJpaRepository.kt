package com.comforest.core.service

import org.springframework.data.jpa.repository.JpaRepository

internal interface ServiceJpaRepository : JpaRepository<ServiceEntity, Long>
