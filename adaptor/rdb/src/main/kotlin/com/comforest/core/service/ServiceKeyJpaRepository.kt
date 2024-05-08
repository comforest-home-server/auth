package com.comforest.core.service

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

internal interface ServiceKeyJpaRepository : JpaRepository<ServiceKeyEntity, Long> {
    @Query(
        "select e from ServiceKeyEntity e " +
            "join fetch e.service " +
            "where e.key = :key",
    )
    fun findByKey(key: String): ServiceKeyEntity?
}
