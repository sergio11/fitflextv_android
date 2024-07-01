package com.dreamsoftware.fitflextv.data.repository.workout

import com.dreamsoftware.fitflextv.data.entities.FavList
import com.dreamsoftware.fitflextv.data.entities.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {
    fun getWorkouts(): List<Workout>

    fun getWorkoutById(id: String): Workout

    fun getFavoritesWorkouts(): Flow<FavList>
}