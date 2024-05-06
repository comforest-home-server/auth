package com.comforest.core.user

import com.comforest.core.NotFoundUserException
import com.comforest.core.auth.UserId
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class UserClient(
    private val userRepository: UserRepository,
) : UserQueryRepository, UserCommandRepository {
    override fun getUser(userId: UserId): User? {
        val userEntity = userRepository.findByIdOrNull(userId.value) ?: throw NotFoundUserException

        return userEntity.toDomain()
    }

    @Transactional
    override fun updateUser(user: User) {
        val entity = userRepository.findByIdOrNull(user.id.value) ?: throw NotFoundUserException

        entity.name = user.nickname

        userRepository.save(entity)
    }

    override fun deleteUser(userId: UserId) {
        userRepository.deleteById(userId.value)
    }
}

private fun UserEntity.toDomain(): User = User(
    id = UserId(this.id),
    nickname = this.name,
)
