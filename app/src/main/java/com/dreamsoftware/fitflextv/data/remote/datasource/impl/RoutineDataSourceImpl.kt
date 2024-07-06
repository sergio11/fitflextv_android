package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesException
import com.dreamsoftware.fitflextv.data.remote.exception.FirebaseException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class RoutineDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val routineMapper: IOneSideMapper<Map<String, Any?>, RoutineDTO>,
    private val dispatcher: CoroutineDispatcher
): IRoutineDataSource {

    private companion object {
        const val COLLECTION_NAME = "routines"
    }

    @Throws(FetchRemoteRoutinesException::class)
    override suspend fun getRoutines(): List<RoutineDTO> = withContext(dispatcher)  {
        try {
            val snapshot = firebaseStore.collection(COLLECTION_NAME)
                .get()
                .await()
            snapshot.documents.map { document ->
                routineMapper.mapInToOut(
                    document.data ?: throw FetchRemoteRoutinesException("routines data is null")
                )
            }
        } catch (ex: FirebaseException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteRoutinesException(
                "An error occurred when trying to fetch routines",
                ex
            )
        }
    }

    @Throws(FetchRemoteRoutineException::class)
    override suspend fun getRoutineById(id: String): RoutineDTO = withContext(dispatcher)  {
        try {
            val document = firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .await()
            routineMapper.mapInToOut(
                document.data ?: throw FetchRemoteRoutineException("routine data is null")
            )
        } catch (ex: FirebaseException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteRoutineException(
                "An error occurred when trying to fetch the routine with ID $id",
                ex
            )
        }
    }
}