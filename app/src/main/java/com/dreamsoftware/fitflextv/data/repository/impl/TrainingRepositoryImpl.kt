package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ITrainingRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.TrainingDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteTrainingsRecommendedException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsRecommendedException
import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class TrainingRepositoryImpl(
    private val trainingRemoteDataSource: ITrainingRemoteDataSource,
    private val trainingMapper: IOneSideMapper<TrainingDTO, TrainingBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ITrainingRepository {

    @Throws(FetchTrainingsRecommendedException::class)
    override suspend fun getTrainingsRecommended(): List<TrainingBO> = safeExecute {
        try {
            trainingRemoteDataSource
                .getTrainingsRecommended()
                .let(trainingMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchRemoteTrainingsRecommendedException) {
            throw FetchTrainingsRecommendedException("An error occurred when fetching trainings", ex)
        }
    }

    @Throws(FetchTrainingByIdException::class)
    override suspend fun getTrainingById(id: String): TrainingBO = safeExecute {
        try {
            trainingRemoteDataSource
                .getTrainingById(id)
                .let(trainingMapper::mapInToOut)
        } catch (ex: FetchRemoteTrainingsRecommendedException) {
            throw FetchTrainingsRecommendedException("An error occurred when fetching trainings", ex)
        }
    }
}