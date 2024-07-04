package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.domain.model.TrainingDetailsBO

interface ITrainingRepository {

    suspend fun getTrainingsRecommended(): List<TrainingBO>
    suspend fun getTrainingById(id: Int): TrainingDetailsBO
}