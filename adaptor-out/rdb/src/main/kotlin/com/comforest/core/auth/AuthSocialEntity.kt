package com.comforest.core.auth

import com.comforest.core.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table

@Table(name = "auth_social")
@Entity
@IdClass(SocialInfo::class)
internal class AuthSocialEntity(
    socialType: SocialType,
    socialId: String,
    userId: Long,
) : AuditingEntity() {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", columnDefinition = "varchar(10)")
    var socialType: SocialType = socialType
        protected set

    @Id
    @Column(name = "social_id")
    var socialId: String = socialId
        protected set

    @Column(name = "user_id")
    var userId: Long = userId
        protected set
}
