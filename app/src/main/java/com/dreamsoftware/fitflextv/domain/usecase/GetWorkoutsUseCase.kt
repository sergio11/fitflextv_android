package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.repository.IWorkoutRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetWorkoutsUseCase(
    private val workoutRepository: IWorkoutRepository
) : BaseUseCase<List<WorkoutBO>>() {
    override suspend fun onExecuted():  List<WorkoutBO> =
        workoutRepository.getWorkouts()
}