package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetFeaturedTrainingsUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCase<List<ITrainingProgramBO>>() {
    override suspend fun onExecuted(): List<ITrainingProgramBO> =
        trainingRepository.getTrainingsRecommended().toList()
}