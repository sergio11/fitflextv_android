package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesException

interface IRoutineRemoteDataSource {

    @Throws(FetchRemoteRoutinesException::class)
    suspend fun getRoutines(): List<RoutineDTO>

    @Throws(FetchRemoteRoutineByIdException::class)
    suspend fun getRoutineById(id: String): RoutineDTO
}