package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedRoutinesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByCategoryExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesExceptionRemote
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
        const val IS_FEATURED_FIELD = "isFeatured"
    }

    @Throws(FetchRemoteRoutinesExceptionRemote::class)
    override suspend fun getRoutines(filter: TrainingFilterDTO): List<RoutineDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { routineMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteRoutinesExceptionRemote("An error occurred when trying to fetch routines", ex)
    }

    @Throws(FetchRemoteRoutineByIdExceptionRemote::class)
    override suspend fun getRoutineById(id: String): RoutineDTO = try {
            fetchSingleFromFireStore(
                query = { firebaseStore.collection(COLLECTION_NAME)
                    .document(id)
                    .get() },
                mapper = { routineMapper.mapInToOut(it) }
            )
    } catch (ex: Exception) {
        throw FetchRemoteRoutineByIdExceptionRemote("An error occurred when trying to fetch the routine with ID $id", ex)
    }

    @Throws(FetchRemoteRoutineByCategoryExceptionRemote::class)
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
        throw FetchRemoteRoutineByCategoryExceptionRemote("An error occurred when trying to fetch routines by category", ex)
    }

    @Throws(FetchRemoteFeaturedRoutinesExceptionRemote::class)
    override suspend fun getFeaturedRoutines(): List<RoutineDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(IS_FEATURED_FIELD, true)
                    .get()
            },
            mapper = { data -> routineMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteFeaturedRoutinesExceptionRemote("An error occurred when trying to fetch featured routines", ex)
    }
}