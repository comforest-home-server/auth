package com.comforest.core.service

import com.comforest.core.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity
@Table(name = "service")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE service SET deleted=true WHERE service_id = ?")
internal class ServiceEntity : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    val id: Long = 0

    @Column(name = "description")
    val description: String = ""

    @Column(name = "deleted")
    var deleted: Boolean = false
        protected set
}
