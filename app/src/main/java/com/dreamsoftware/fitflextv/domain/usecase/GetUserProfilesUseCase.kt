package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetUserProfilesUseCase(
    private val userRepository: IUserRepository
) : BaseUseCase<List<ProfileBO>>() {
    override suspend fun onExecuted(): List<ProfileBO> =
        userRepository.getUserProfiles()
}