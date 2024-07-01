package com.dreamsoftware.fitflextv.data.repository.training

import com.dreamsoftware.fitflextv.data.entities.Training
import com.dreamsoftware.fitflextv.data.entities.TrainingDetails
import kotlinx.coroutines.flow.Flow

interface TrainingRepository {

    suspend fun getTrainingsRecommended(): List<Training>
    fun getTrainingById(id: Int): Flow<TrainingDetails>
}