package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.TrainingDetailsBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetTrainingByIdUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCaseWithParams<GetTrainingByIdUseCase.Params, TrainingDetailsBO>() {

    override suspend fun onExecuted(params: Params): TrainingDetailsBO =
        trainingRepository.getTrainingById(params.id)

    data class Params(
        val id: Int
    )
}