package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRecommendedWorkoutsRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchFeaturedWorkoutsRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchWorkoutByCategoryRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchWorkoutByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchWorkoutsRemoteException

interface IWorkoutRemoteDataSource {

    @Throws(FetchWorkoutsRemoteException::class)
    suspend fun getWorkouts(filter: TrainingFilterDTO): List<WorkoutDTO>

    @Throws(FetchWorkoutByIdRemoteException::class)
    suspend fun getWorkoutById(id: String): WorkoutDTO

    @Throws(FetchWorkoutByIdRemoteException::class)
    suspend fun getWorkoutByIdList(idList: List<String>): List<WorkoutDTO>

    @Throws(FetchWorkoutByCategoryRemoteException::class)
    suspend fun getWorkoutByCategory(id: String): List<WorkoutDTO>

    @Throws(FetchFeaturedWorkoutsRemoteException::class)
    suspend fun getFeaturedWorkouts(): List<WorkoutDTO>

    @Throws(FetchRecommendedWorkoutsRemoteException::class)
    suspend fun getRecommendedWorkouts(): List<WorkoutDTO>
}