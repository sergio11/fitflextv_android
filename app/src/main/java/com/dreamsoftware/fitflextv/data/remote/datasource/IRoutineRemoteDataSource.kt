package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedRoutinesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByCategoryExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesExceptionRemote

interface IRoutineRemoteDataSource {

    @Throws(FetchRemoteRoutinesExceptionRemote::class)
    suspend fun getRoutines(): List<RoutineDTO>

    @Throws(FetchRemoteRoutineByIdExceptionRemote::class)
    suspend fun getRoutineById(id: String): RoutineDTO

    @Throws(FetchRemoteRoutineByCategoryExceptionRemote::class)
    suspend fun getRoutineByCategory(categoryId: String): List<RoutineDTO>

    @Throws(FetchRemoteFeaturedRoutinesExceptionRemote::class)
    suspend fun getFeaturedRoutines(): List<RoutineDTO>
}