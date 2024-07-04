package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.FavWorkout
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO

interface IWorkoutRepository {
    suspend fun getWorkouts(): List<WorkoutBO>

    suspend fun getWorkoutById(id: String): WorkoutBO

    suspend fun getFavoritesWorkouts(): List<FavWorkout>
}