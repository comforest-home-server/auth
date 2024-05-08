package com.comforest.core.service

import com.comforest.core.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "service_key")
internal class ServiceKeyEntity : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_key_id")
    var id: Long = 0
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    lateinit var service: ServiceEntity
        protected set

    @Column(name = "key_value")
    var key: String = ""
        protected set
}
