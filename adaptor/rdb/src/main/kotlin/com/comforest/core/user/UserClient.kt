package com.comforest.core.user

import com.comforest.core.NotFoundUserException
import com.comforest.core.auth.UserId
import jakarta.transaction.Transactional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class UserClient(
    private val userJpaRepository: UserJpaRepository,
) : UserQueryRepository, UserCommandRepository {
    override suspend fun getUser(userId: UserId): User? = withContext(Dispatchers.IO) {
        val userEntity = userJpaRepository.findByIdOrNull(userId.value) ?: throw NotFoundUserException

        userEntity.toDomain()
    }

    @Transactional
    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            val entity = userJpaRepository.findByIdOrNull(user.id.value) ?: throw NotFoundUserException

            entity.name = user.nickname

            userJpaRepository.save(entity)
        }
    }

    override suspend fun deleteUser(userId: UserId) = withContext(Dispatchers.IO) {
        userJpaRepository.deleteById(userId.value)
    }
}

private fun UserEntity.toDomain(): User = User(
    id = UserId(this.id),
    nickname = this.name,
)
