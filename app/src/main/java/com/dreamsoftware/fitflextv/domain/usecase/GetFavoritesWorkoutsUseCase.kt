package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.FavWorkout
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetFavoritesWorkoutsUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCase<List<FavWorkout>>() {

    override suspend fun onExecuted(): List<FavWorkout> =
        emptyList()
}