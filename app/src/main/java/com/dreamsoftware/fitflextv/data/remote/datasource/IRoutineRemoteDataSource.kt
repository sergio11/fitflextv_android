package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedRoutinesException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByCategoryException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesException

interface IRoutineRemoteDataSource {

    @Throws(FetchRemoteRoutinesException::class)
    suspend fun getRoutines(): List<RoutineDTO>

    @Throws(FetchRemoteRoutineByIdException::class)
    suspend fun getRoutineById(id: String): RoutineDTO

    @Throws(FetchRemoteRoutineByCategoryException::class)
    suspend fun getRoutineByCategory(categoryId: String): List<RoutineDTO>

    @Throws(FetchRemoteFeaturedRoutinesException::class)
    suspend fun getFeaturedRoutines(): List<RoutineDTO>
}