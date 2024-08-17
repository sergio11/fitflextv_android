package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeUseCase

class GetProfileSelectedUseCase(
    private val userRepository: IUserRepository,
    private val profilesRepository: IProfilesRepository
): FudgeUseCase<ProfileBO>() {
    override suspend fun onExecuted(): ProfileBO =
        userRepository.getAuthenticatedUid()
            .let { profilesRepository.getProfileSelectedByUser(it) }
}