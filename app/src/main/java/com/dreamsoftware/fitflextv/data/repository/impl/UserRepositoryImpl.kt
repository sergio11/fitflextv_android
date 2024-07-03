package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import kotlinx.coroutines.CoroutineDispatcher

internal class UserRepositoryImpl(
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IUserRepository {
    override suspend fun getUserProfiles(): List<ProfileBO> {
        return listOf(
            ProfileBO(
                id = "1",
                name = "Liam",
                avatar = "https://github.com/TheChance101/tv-samples/assets/59895284/4fd7ab6d-02d1-4f0c-b118-ffff98e71f3a"
            ),
            ProfileBO(
                id = "2",
                name = "Olivia",
                avatar = "https://github.com/TheChance101/tv-samples/assets/59895284/3cfb4d9c-55d6-4568-961c-fb1dae786814"
            ),
            ProfileBO(
                id = "3",
                name = "Noah",
                avatar = "https://github.com/TheChance101/tv-samples/assets/59895284/a0f931d1-e29f-41e9-8e03-b70ad8e479df"
            ),
        )
    }
}