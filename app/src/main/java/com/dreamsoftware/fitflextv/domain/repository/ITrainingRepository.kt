package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsRecommendedException
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum

interface ITrainingRepository {

    @Throws(FetchTrainingsException::class)
    suspend fun getTrainings(type: TrainingTypeEnum): Iterable<ITrainingProgramBO>

    @Throws(FetchTrainingByIdException::class)
    suspend fun getTrainingById(id: String, type: TrainingTypeEnum): ITrainingProgramBO

    @Throws(FetchTrainingsRecommendedException::class)
    suspend fun getTrainingsRecommended(): Iterable<ITrainingProgramBO>
}