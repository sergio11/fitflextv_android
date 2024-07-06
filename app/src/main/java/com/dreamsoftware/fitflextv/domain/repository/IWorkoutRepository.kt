package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchWorkoutByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchWorkoutsException
import com.dreamsoftware.fitflextv.domain.model.FavWorkout
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO

interface IWorkoutRepository {

    @Throws(FetchWorkoutsException::class)
    suspend fun getWorkouts(): List<WorkoutBO>

    @Throws(FetchWorkoutByIdException::class)
    suspend fun getWorkoutById(id: String): WorkoutBO

    suspend fun getFavoritesWorkouts(): List<FavWorkout>
}