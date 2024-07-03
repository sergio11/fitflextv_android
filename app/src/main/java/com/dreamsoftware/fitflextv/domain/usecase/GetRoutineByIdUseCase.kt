package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.repository.IRoutineRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetRoutineByIdUseCase(
    private val routineRepository: IRoutineRepository
) : BaseUseCaseWithParams<GetRoutineByIdUseCase.Params, RoutineBO>() {
    override suspend fun onExecuted(params: Params): RoutineBO =
        routineRepository.getRoutineById(params.id)

    data class Params(
        val id: String
    )
}