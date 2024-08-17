package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeUseCase

class GetUserProfilesUseCase(
    private val userRepository: IUserRepository,
    private val profilesRepository: IProfilesRepository
) : FudgeUseCase<List<ProfileBO>>() {

    override suspend fun onExecuted(): List<ProfileBO> {
        val userId = userRepository.getAuthenticatedUid()
        return profilesRepository.getProfilesByUser(userId)
    }
}