package com.dreamsoftware.fitflextv.data.repository.training

import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.domain.model.TrainingDetailsBO
import kotlinx.coroutines.flow.Flow

interface TrainingRepository {

    suspend fun getTrainingsRecommended(): List<TrainingBO>
    fun getTrainingById(id: Int): Flow<TrainingDetailsBO>
}