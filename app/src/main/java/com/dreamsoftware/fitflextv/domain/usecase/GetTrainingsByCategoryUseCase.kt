package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetTrainingsByCategoryUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCaseWithParams<GetTrainingsByCategoryUseCase.Params, List<ITrainingProgramBO>>() {
    override suspend fun onExecuted(params: Params): List<ITrainingProgramBO> =
        trainingRepository.getTrainingsByCategory(params.id).toList()

    data class Params(
        val id: String
    )
}