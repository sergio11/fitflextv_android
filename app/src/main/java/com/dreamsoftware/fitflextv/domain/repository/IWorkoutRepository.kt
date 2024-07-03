package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.FavListBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import kotlinx.coroutines.flow.Flow

interface IWorkoutRepository {
    fun getWorkouts(): List<WorkoutBO>

    fun getWorkoutById(id: String): WorkoutBO

    fun getFavoritesWorkouts(): Flow<FavListBO>
}