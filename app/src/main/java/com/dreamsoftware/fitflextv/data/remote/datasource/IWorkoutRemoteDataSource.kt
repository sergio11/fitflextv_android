package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByCategoryException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutsException

interface IWorkoutRemoteDataSource {

    @Throws(FetchRemoteWorkoutsException::class)
    suspend fun getWorkouts(): List<WorkoutDTO>

    @Throws(FetchRemoteWorkoutByIdException::class)
    suspend fun getWorkoutById(id: String): WorkoutDTO

    @Throws(FetchRemoteWorkoutByIdException::class)
    suspend fun getWorkoutByIdList(idList: List<String>): List<WorkoutDTO>

    @Throws(FetchRemoteWorkoutByCategoryException::class)
    suspend fun getWorkoutByCategory(id: String): List<WorkoutDTO>
}