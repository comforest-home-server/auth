package com.comforest.core.user

import com.comforest.core.NotFoundUserException
import com.comforest.core.auth.UserId
import jakarta.transaction.Transactional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class UserClient(
    private val userRepository: UserRepository,
) : UserQueryRepository, UserCommandRepository {
    override suspend fun getUser(userId: UserId): User? = withContext(Dispatchers.IO) {
        val userEntity = userRepository.findByIdOrNull(userId.value) ?: throw NotFoundUserException

        userEntity.toDomain()
    }

    @Transactional
    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            val entity = userRepository.findByIdOrNull(user.id.value) ?: throw NotFoundUserException

            entity.name = user.nickname

            userRepository.save(entity)
        }
    }

    override suspend fun deleteUser(userId: UserId) = withContext(Dispatchers.IO) {
        userRepository.deleteById(userId.value)
    }
}

private fun UserEntity.toDomain(): User = User(
    id = UserId(this.id),
    nickname = this.name,
)
