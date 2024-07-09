package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetTrainingByIdUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCaseWithParams<GetTrainingByIdUseCase.Params, TrainingBO>() {

    override suspend fun onExecuted(params: Params): TrainingBO =
        trainingRepository.getTrainingById(params.id)

    data class Params(
        val id: String
    )
}