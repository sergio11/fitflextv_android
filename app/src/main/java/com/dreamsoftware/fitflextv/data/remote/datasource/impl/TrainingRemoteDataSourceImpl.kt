package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ITrainingRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.TrainingDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteTrainingByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteTrainingsRecommendedException
import com.dreamsoftware.fitflextv.data.remote.exception.FirebaseException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class TrainingRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val trainingsMapper: IOneSideMapper<Map<String, Any?>, TrainingDTO>,
    private val dispatcher: CoroutineDispatcher
): ITrainingRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "trainings"
    }

    @Throws(FetchRemoteTrainingsRecommendedException::class)
    override suspend fun getTrainingsRecommended(): List<TrainingDTO> = withContext(dispatcher)  {
        try {
            val snapshot = firebaseStore.collection(COLLECTION_NAME)
                .get()
                .await()
            snapshot.documents.map { document ->
                trainingsMapper.mapInToOut(
                    document.data ?: throw FetchRemoteTrainingsRecommendedException("trainings data is null")
                )
            }
        } catch (ex: FirebaseException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteTrainingsRecommendedException(
                "An error occurred when trying to fetch trainings",
                ex
            )
        }
    }

    @Throws(FetchRemoteTrainingByIdException::class)
    override suspend fun getTrainingById(id: String): TrainingDTO = withContext(dispatcher)  {
        try {
            val document = firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .await()
            trainingsMapper.mapInToOut(
                document.data ?: throw FetchRemoteTrainingByIdException("training data is null")
            )
        } catch (ex: FirebaseException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteTrainingByIdException(
                "An error occurred when trying to fetch the training with ID $id",
                ex
            )
        }
    }
}