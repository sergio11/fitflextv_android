package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByCategoryException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class RoutineRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val routineMapper: IOneSideMapper<Map<String, Any?>, RoutineDTO>,
    dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IRoutineRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "routines"
        const val CATEGORY_FIELD = "category"
    }

    @Throws(FetchRemoteRoutinesException::class)
    override suspend fun getRoutines(): List<RoutineDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { routineMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteRoutinesException("An error occurred when trying to fetch routines", ex)
    }

    @Throws(FetchRemoteRoutineByIdException::class)
    override suspend fun getRoutineById(id: String): RoutineDTO = try {
            fetchSingleFromFireStore(
                query = { firebaseStore.collection(COLLECTION_NAME)
                    .document(id)
                    .get() },
                mapper = { routineMapper.mapInToOut(it) }
            )
    } catch (ex: Exception) {
        throw FetchRemoteRoutineByIdException("An error occurred when trying to fetch the routine with ID $id", ex)
    }

    @Throws(FetchRemoteRoutineByCategoryException::class)
    override suspend fun getRoutineByCategory(categoryId: String): List<RoutineDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(CATEGORY_FIELD, categoryId)
                    .get()
            },
            mapper = { data -> routineMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteRoutineByCategoryException("An error occurred when trying to fetch routines by category", ex)
    }
}