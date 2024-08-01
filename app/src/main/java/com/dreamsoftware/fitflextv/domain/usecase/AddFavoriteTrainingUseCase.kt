package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.AddFavoriteTrainingBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class AddFavoriteTrainingUseCase(
    private val userRepository: IUserRepository,
    private val trainingRepository: ITrainingRepository
): BaseUseCaseWithParams<AddFavoriteTrainingUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        toAddFavoriteTrainingBO(userRepository.getAuthenticatedUid())
            .let { trainingRepository.addFavoriteTraining(it) }
    }

    private fun Params.toAddFavoriteTrainingBO(userId: String) = AddFavoriteTrainingBO(
        userId = userId,
        trainingId = trainingId,
        trainingType = trainingType
    )

    data class Params(
        val trainingId: String,
        val trainingType: TrainingTypeEnum
    )
}