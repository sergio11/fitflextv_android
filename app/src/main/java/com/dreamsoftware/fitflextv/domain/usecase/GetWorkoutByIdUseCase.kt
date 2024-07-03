package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.repository.IWorkoutRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetWorkoutByIdUseCase(
    private val workoutRepository: IWorkoutRepository
) : BaseUseCaseWithParams<GetWorkoutByIdUseCase.Params, WorkoutBO>() {
    override suspend fun onExecuted(params: Params): WorkoutBO =
        workoutRepository.getWorkoutById(params.id)

    data class Params(
        val id: String
    )
}