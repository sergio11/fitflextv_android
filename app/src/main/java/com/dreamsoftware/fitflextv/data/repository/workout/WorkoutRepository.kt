package com.dreamsoftware.fitflextv.data.repository.workout

import com.dreamsoftware.fitflextv.domain.model.FavListBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {
    fun getWorkouts(): List<WorkoutBO>

    fun getWorkoutById(id: String): WorkoutBO

    fun getFavoritesWorkouts(): Flow<FavListBO>
}