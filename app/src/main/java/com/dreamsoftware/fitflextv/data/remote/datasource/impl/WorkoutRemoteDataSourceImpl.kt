package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutsException
import com.dreamsoftware.fitflextv.data.remote.exception.FirebaseException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class WorkoutRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val seriesMapper: IOneSideMapper<Map<String, Any?>, WorkoutDTO>,
    private val dispatcher: CoroutineDispatcher
): IWorkoutRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "workouts"
    }

    @Throws(FetchRemoteWorkoutsException::class)
    override suspend fun getWorkouts(): List<WorkoutDTO> = withContext(dispatcher)  {
        try {
            val snapshot = firebaseStore.collection(COLLECTION_NAME)
                .get()
                .await()
            snapshot.documents.map { document ->
                seriesMapper.mapInToOut(
                    document.data ?: throw FetchRemoteWorkoutsException("workouts data is null")
                )
            }
        } catch (ex: FirebaseException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteWorkoutsException(
                "An error occurred when trying to fetch workouts",
                ex
            )
        }
    }

    @Throws(FetchRemoteWorkoutByIdException::class)
    override suspend fun getWorkoutById(id: String): WorkoutDTO = withContext(dispatcher)  {
        try {
            val document = firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .await()
            seriesMapper.mapInToOut(
                document.data ?: throw FetchRemoteWorkoutByIdException("workout data is null")
            )
        } catch (ex: FirebaseException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteWorkoutByIdException(
                "An error occurred when trying to fetch the workout with ID $id",
                ex
            )
        }
    }
}