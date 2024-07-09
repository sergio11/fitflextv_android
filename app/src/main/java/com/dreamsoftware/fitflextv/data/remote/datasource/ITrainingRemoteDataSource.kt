package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.TrainingDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteTrainingByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteTrainingsRecommendedException

interface ITrainingRemoteDataSource {

    @Throws(FetchRemoteTrainingsRecommendedException::class)
    suspend fun getTrainingsRecommended(): List<TrainingDTO>

    @Throws(FetchRemoteTrainingByIdException::class)
    suspend fun getTrainingById(id: String): TrainingDTO
}