package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.FavoriteTrainingDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AddToFavoritesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.GetFavoritesByUserExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.HasTrainingInFavoritesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.RemoveFromFavoritesExceptionRemote

interface IFavoritesRemoteDataSource {

    @Throws(AddToFavoritesExceptionRemote::class)
    suspend fun addFavorite(data: AddFavoriteTrainingDTO): Boolean

    @Throws(GetFavoritesByUserExceptionRemote::class)
    suspend fun getFavoritesByUser(userId: String): List<FavoriteTrainingDTO>

    @Throws(HasTrainingInFavoritesExceptionRemote::class)
    suspend fun hasTrainingInFavorites(userId: String, trainingId: String): Boolean

    @Throws(RemoveFromFavoritesExceptionRemote::class)
    suspend fun removeFavorite(userId: String, trainingId: String): Boolean
}