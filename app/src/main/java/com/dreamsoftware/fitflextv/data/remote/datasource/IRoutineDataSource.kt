package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesException

interface IRoutineDataSource {

    @Throws(FetchRemoteRoutinesException::class)
    suspend fun getRoutines(): List<RoutineDTO>

    @Throws(FetchRemoteRoutineException::class)
    suspend fun getRoutineById(id: String): RoutineDTO
}