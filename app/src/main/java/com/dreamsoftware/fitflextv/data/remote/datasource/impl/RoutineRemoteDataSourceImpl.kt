package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchFeaturedRoutinesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRoutineByCategoryRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRoutineByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRoutinesRecommendedRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRoutinesRemoteException
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineDispatcher

internal class RoutineRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val routineMapper: IOneSideMapper<Map<String, Any?>, RoutineDTO>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), IRoutineRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "routines"
        const val CATEGORY_FIELD = "category"
        const val IS_FEATURED_FIELD = "isFeatured"
        const val IS_RECOMMENDED_FIELD = "isRecommended"
        const val LANGUAGE = "language"
        const val DURATION = "duration"
        const val INTENSITY = "intensity"
        const val RELEASED_DATE = "releasedDate"
        const val WORKOUT_TYPE = "workoutType"
    }

    @Throws(FetchRoutinesRemoteException::class)
    override suspend fun getRoutines(filter: TrainingFilterDTO): List<RoutineDTO> = try {
        fetchListFromFireStore(
            query = {
                with(filter) {
                    var query: Query = firebaseStore.collection(COLLECTION_NAME)
                    classLanguage?.let { query = query.whereEqualTo(LANGUAGE, it) }
                    intensity?.let { query = query.whereEqualTo(INTENSITY, it) }
                    workoutType?.let { query = query.whereEqualTo(WORKOUT_TYPE, it) }
                    videoLength?.let {
                        query = query
                            .whereGreaterThanOrEqualTo(DURATION, it.first)
                            .whereLessThanOrEqualTo(DURATION, it.last)
                    }
                    // Apply sorting
                    if (priorityFeatured) {
                        query = query.orderBy(IS_FEATURED_FIELD, Query.Direction.DESCENDING)
                    }
                    query = if (orderByReleasedDateDesc) {
                        query.orderBy(RELEASED_DATE, Query.Direction.DESCENDING)
                    } else {
                        query.orderBy(RELEASED_DATE, Query.Direction.ASCENDING)
                    }
                    query.get()
                }
            },
            mapper = { routineMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRoutinesRemoteException(
            "An error occurred when trying to fetch routines",
            ex
        )
    }

    @Throws(FetchRoutineByIdRemoteException::class)
    override suspend fun getRoutineById(id: String): RoutineDTO = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(id)
                    .get()
            },
            mapper = { routineMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRoutineByIdRemoteException(
            "An error occurred when trying to fetch the routine with ID $id",
            ex
        )
    }

    @Throws(FetchRoutineByCategoryRemoteException::class)
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
        throw FetchRoutineByCategoryRemoteException(
            "An error occurred when trying to fetch routines by category",
            ex
        )
    }

    @Throws(FetchFeaturedRoutinesRemoteException::class)
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
        throw FetchFeaturedRoutinesRemoteException(
            "An error occurred when trying to fetch featured routines",
            ex
        )
    }

    @Throws(FetchRoutinesRecommendedRemoteException::class)
    override suspend fun getRecommendedRoutines(): List<RoutineDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(IS_RECOMMENDED_FIELD, true)
                    .get()
            },
            mapper = { data -> routineMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRoutinesRecommendedRemoteException(
            "An error occurred when trying to fetch recommended routines",
            ex
        )
    }
}