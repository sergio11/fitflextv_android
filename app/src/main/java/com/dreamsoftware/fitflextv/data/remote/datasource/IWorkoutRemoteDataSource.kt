package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedWorkoutsExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByCategoryExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutsExceptionRemote

interface IWorkoutRemoteDataSource {

    @Throws(FetchRemoteWorkoutsExceptionRemote::class)
    suspend fun getWorkouts(filter: TrainingFilterDTO): List<WorkoutDTO>

    @Throws(FetchRemoteWorkoutByIdExceptionRemote::class)
    suspend fun getWorkoutById(id: String): WorkoutDTO

    @Throws(FetchRemoteWorkoutByIdExceptionRemote::class)
    suspend fun getWorkoutByIdList(idList: List<String>): List<WorkoutDTO>

    @Throws(FetchRemoteWorkoutByCategoryExceptionRemote::class)
    suspend fun getWorkoutByCategory(id: String): List<WorkoutDTO>

    @Throws(FetchRemoteFeaturedWorkoutsExceptionRemote::class)
    suspend fun getFeaturedWorkouts(): List<WorkoutDTO>
}