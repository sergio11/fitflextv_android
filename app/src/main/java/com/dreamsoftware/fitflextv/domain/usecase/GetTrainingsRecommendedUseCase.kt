package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetTrainingsRecommendedUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCase<List<TrainingBO>>() {
    override suspend fun onExecuted(): List<TrainingBO> =
        trainingRepository.getTrainingsRecommended()
}