package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.AddFavoriteTrainingException
import com.dreamsoftware.fitflextv.domain.exception.FetchFavoritesTrainingsByUserException
import com.dreamsoftware.fitflextv.domain.exception.FetchFeaturedTrainingsException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingByCategoryException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsRecommendedException
import com.dreamsoftware.fitflextv.domain.exception.RemoveFavoriteTrainingException
import com.dreamsoftware.fitflextv.domain.exception.VerifyFavoriteTrainingException
import com.dreamsoftware.fitflextv.domain.model.AddFavoriteTrainingBO
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum

interface ITrainingRepository {

    @Throws(FetchTrainingsException::class)
    suspend fun getTrainings(data: TrainingFilterDataBO, includePremium: Boolean = false): Iterable<ITrainingProgramBO>

    @Throws(FetchTrainingByIdException::class)
    suspend fun getTrainingById(id: String, type: TrainingTypeEnum): ITrainingProgramBO

    @Throws(FetchTrainingsRecommendedException::class)
    suspend fun getTrainingsRecommended(includePremium: Boolean = false): Iterable<ITrainingProgramBO>

    @Throws(FetchFeaturedTrainingsException::class)
    suspend fun getFeaturedTrainings(includePremium: Boolean = false): Iterable<ITrainingProgramBO>

    @Throws(FetchTrainingByCategoryException::class)
    suspend fun getTrainingsByCategory(id: String, includePremium: Boolean = false): Iterable<ITrainingProgramBO>

    @Throws(AddFavoriteTrainingException::class)
    suspend fun addFavoriteTraining(data: AddFavoriteTrainingBO): Boolean

    @Throws(FetchFavoritesTrainingsByUserException::class)
    suspend fun getFavoritesTrainingsByProfile(profileId: String): List<ITrainingProgramBO>

    @Throws(VerifyFavoriteTrainingException::class)
    suspend fun hasTrainingInFavorites(profileId: String, trainingId: String): Boolean

    @Throws(RemoveFavoriteTrainingException::class)
    suspend fun removeFavoriteTraining(profileId: String, trainingId: String): Boolean
}