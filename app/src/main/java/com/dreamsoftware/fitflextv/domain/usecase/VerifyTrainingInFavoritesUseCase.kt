package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class VerifyTrainingInFavoritesUseCase(
    private val userRepository: IUserRepository,
    private val trainingRepository: ITrainingRepository
): BaseUseCaseWithParams<VerifyTrainingInFavoritesUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        trainingRepository.hasTrainingInFavorites(
            profileId = userRepository.getAuthenticatedUid(),
            trainingId = trainingId
        )
    }

    data class Params(
        val trainingId: String
    )
}