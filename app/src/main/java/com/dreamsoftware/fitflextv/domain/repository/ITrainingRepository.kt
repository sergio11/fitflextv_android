package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsRecommendedException
import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import kotlin.jvm.Throws

interface ITrainingRepository {

    @Throws(FetchTrainingsRecommendedException::class)
    suspend fun getTrainingsRecommended(): List<TrainingBO>

    @Throws(FetchTrainingByIdException::class)
    suspend fun getTrainingById(id: String): TrainingBO
}