package com.comforest.core.auth

import com.comforest.core.AuditingEntity
import com.comforest.core.user.UserEntity
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

@Table(name = "auth_social")
@Entity
internal class AuthSocialEntity(
    socialType: SocialType,
    socialId: String,
    userId: Long,
) : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_social_id")
    val id: Long = 0

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", columnDefinition = "varchar(10)")
    var socialType: SocialType = socialType
        protected set

    @Column(name = "social_id")
    var socialId: String = socialId
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    lateinit var user: UserEntity
        protected set
}
