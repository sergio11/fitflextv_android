package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetFavoritesTrainingsByUserUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val trainingRepository: ITrainingRepository
): FudgeTvUseCase<List<ITrainingProgramBO>>() {

    override suspend fun onExecuted(): List<ITrainingProgramBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(userUid)
        return trainingRepository.getFavoritesTrainingsByProfile(profileSelected.uuid)
    }
}