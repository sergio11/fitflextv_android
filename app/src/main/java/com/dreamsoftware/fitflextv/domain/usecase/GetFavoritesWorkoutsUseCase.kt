package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.FavWorkout
import com.dreamsoftware.fitflextv.domain.repository.IWorkoutRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetFavoritesWorkoutsUseCase(
    private val workoutRepository: IWorkoutRepository
) : BaseUseCase<List<FavWorkout>>() {

    override suspend fun onExecuted(): List<FavWorkout> =
        workoutRepository.getFavoritesWorkouts()
}