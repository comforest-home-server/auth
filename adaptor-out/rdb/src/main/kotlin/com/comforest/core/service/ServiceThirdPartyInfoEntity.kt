package com.comforest.core.service

import com.comforest.core.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "service_third_party_info")
internal class ServiceThirdPartyInfoEntity : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_third_party_info_id")
    val id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    lateinit var service: ServiceEntity
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "third_party_type", columnDefinition = "varchar(100)")
    lateinit var thirdParty: ThirdParty
        protected set

    @Column(name = "third_party_client_id")
    lateinit var clientId: String
        protected set
}
