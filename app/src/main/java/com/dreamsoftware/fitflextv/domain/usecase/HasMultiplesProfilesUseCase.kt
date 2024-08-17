package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class HasMultiplesProfilesUseCase(
    private val userRepository: IUserRepository,
    private val profilesRepository: IProfilesRepository
): FudgeTvUseCase<Boolean>() {
    override suspend fun onExecuted(): Boolean {
        val userUid = userRepository.getAuthenticatedUid()
        return profilesRepository.getProfilesByUser(userUid).size > 1
    }
}