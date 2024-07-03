package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.domain.model.TrainingDetailsBO
import kotlinx.coroutines.flow.Flow

interface ITrainingRepository {

    suspend fun getTrainingsRecommended(): List<TrainingBO>
    fun getTrainingById(id: Int): Flow<TrainingDetailsBO>
}