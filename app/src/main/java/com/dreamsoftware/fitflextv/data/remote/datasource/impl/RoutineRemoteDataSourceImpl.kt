package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesException
import com.dreamsoftware.fitflextv.data.remote.exception.DataSourceException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class RoutineRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val routineMapper: IOneSideMapper<Map<String, Any?>, RoutineDTO>,
    private val dispatcher: CoroutineDispatcher
): IRoutineRemoteDataSource {

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
        } catch (ex: DataSourceException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteRoutinesException(
                "An error occurred when trying to fetch routines",
                ex
            )
        }
    }

    @Throws(FetchRemoteRoutineByIdException::class)
    override suspend fun getRoutineById(id: String): RoutineDTO = withContext(dispatcher)  {
        try {
            val document = firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .await()
            routineMapper.mapInToOut(
                document.data ?: throw FetchRemoteRoutineByIdException("routine data is null")
            )
        } catch (ex: DataSourceException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteRoutineByIdException(
                "An error occurred when trying to fetch the routine with ID $id",
                ex
            )
        }
    }
}