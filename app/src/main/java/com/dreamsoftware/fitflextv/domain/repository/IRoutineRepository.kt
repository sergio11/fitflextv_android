package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchRoutineByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchRoutinesException
import com.dreamsoftware.fitflextv.domain.model.RoutineBO

interface IRoutineRepository {

    @Throws(FetchRoutinesException::class)
    suspend fun getRoutines(): List<RoutineBO>

    @Throws(FetchRoutineByIdException::class)
    suspend fun getRoutineById(id: String): RoutineBO
}