package com.comforest.core.user

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
@Table(name = "user")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE user SET deleted=true, name=null WHERE user_id = ?")
internal class UserEntity : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "service_id")
    val service: Long = 0

    @Column(name = "name")
    var name: String? = null

    @Column(name = "deleted")
    var deleted: Boolean = false
        protected set

    companion object {
        fun of(id: Long): UserEntity =
            UserEntity().apply {
                this.id = id
            }
    }
}
