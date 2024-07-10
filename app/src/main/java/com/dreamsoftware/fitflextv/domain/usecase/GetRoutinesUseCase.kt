package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.repository.IRoutineRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetRoutinesUseCase(
    private val routineRepository: IRoutineRepository
) : BaseUseCase<List<RoutineBO>>() {
    override suspend fun onExecuted():  List<RoutineBO> =
        routineRepository.getRoutines()
}