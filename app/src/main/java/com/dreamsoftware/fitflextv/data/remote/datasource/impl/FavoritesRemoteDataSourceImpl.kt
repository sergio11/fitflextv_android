package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.FavoriteTrainingDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AddToFavoritesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.DeleteRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.GetFavoritesByUserExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.HasTrainingInFavoritesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.RemoveFromFavoritesExceptionRemote
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class FavoritesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val addFavoriteMapper: IOneSideMapper<AddFavoriteTrainingDTO, Map<String, Any?>>,
    private val favoriteMapper: IOneSideMapper<Map<String, Any?>, FavoriteTrainingDTO>,
    private val dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IFavoritesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "favorite_trainings"
        const val SUB_COLLECTION_NAME = "trainings"
    }

    @Throws(AddToFavoritesExceptionRemote::class)
    override suspend fun addFavorite(data: AddFavoriteTrainingDTO): Boolean = try {
            withContext(dispatcher) {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(data.userId)
                    .collection(SUB_COLLECTION_NAME)
                    .document(data.trainingId)
                    .set(addFavoriteMapper.mapInToOut(data), SetOptions.merge())
                    .await()
                true
            }
        } catch (ex: Exception) {
            throw AddToFavoritesExceptionRemote(
                "An error occurred when trying to add training to favorites",
                ex
            )
        }

    @Throws(GetFavoritesByUserExceptionRemote::class)
    override suspend fun getFavoritesByUser(userId: String): List<FavoriteTrainingDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore
                .collection(COLLECTION_NAME)
                .document(userId)
                .collection(SUB_COLLECTION_NAME)
                .get() },
            mapper = { favoriteMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteRoutinesExceptionRemote("An error occurred when trying to fetch routines", ex)
    }

    @Throws(HasTrainingInFavoritesExceptionRemote::class)
    override suspend fun hasTrainingInFavorites(userId: String, trainingId: String): Boolean = try {
        withContext(dispatcher) {
            val document = firebaseStore
                .collection(COLLECTION_NAME)
                .document(userId)
                .collection(SUB_COLLECTION_NAME)
                .document(trainingId)
                .get()
                .await()
            document.exists()
        }
    } catch (ex: Exception) {
        throw HasTrainingInFavoritesExceptionRemote("An error occurred when trying to check favorites", ex)
    }

    @Throws(RemoveFromFavoritesExceptionRemote::class)
    override suspend fun removeFavorite(userId: String, trainingId: String): Boolean = try {
        withContext(dispatcher) {
            firebaseStore
                .collection(COLLECTION_NAME)
                .document(userId)
                .collection(SUB_COLLECTION_NAME)
                .document(trainingId)
                .delete()
                .await()
            true
        }
    } catch (ex: Exception) {
        throw DeleteRemoteProfileExceptionRemote(
            "An error occurred when trying to remove training $trainingId from favorites",
            ex
        )
    }
}