package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ITrainingProgramRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchFeaturedTrainingsRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRecommendedTrainingsRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchTrainingByCategoryRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchTrainingByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchTrainingsRemoteException
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineDispatcher

internal abstract class TrainingProgramRemoteDataSourceImpl<out T>(
    private val collectionName: String,
    private val firebaseStore: FirebaseFirestore,
    private val dataMapper: IOneSideMapper<Map<String, Any?>, T>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), ITrainingProgramRemoteDataSource<T> {

    private companion object {
        const val CATEGORY_FIELD = "category"
        const val ID_FIELD = "uid"
        const val IS_FEATURED_FIELD = "isFeatured"
        const val IS_RECOMMENDED_FIELD = "isRecommended"
        const val LANGUAGE = "language"
        const val DURATION = "duration"
        const val INTENSITY = "intensity"
        const val RELEASED_DATE = "releasedDate"
        const val WORKOUT_TYPE = "workoutType"
        const val INSTRUCTOR = "instructor"
    }

    @Throws(FetchTrainingsRemoteException::class)
    override suspend fun getTrainings(filter: TrainingFilterDTO): List<T> = try {
        fetchListFromFireStore(
            query = {
                with(filter) {
                    var query: Query = firebaseStore.collection(collectionName)
                    classLanguage?.let { query = query.whereEqualTo(LANGUAGE, it) }
                    intensity?.let { query = query.whereEqualTo(INTENSITY, it) }
                    workoutType?.let { query = query.whereEqualTo(WORKOUT_TYPE, it) }
                    instructor?.let { query = query.whereEqualTo(INSTRUCTOR, it) }
                    videoLength?.let {
                        query = query.whereGreaterThanOrEqualTo(DURATION, it.first.toString())
                            .whereLessThanOrEqualTo(DURATION, it.last.toString())
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
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchTrainingsRemoteException(
            "An error occurred when trying to fetch trainings",
            ex
        )
    }

    @Throws(FetchTrainingByIdRemoteException::class)
    override suspend fun getTrainingById(id: String): T = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(collectionName).document(id).get() },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchTrainingByIdRemoteException(
            "An error occurred when trying to fetch the training with ID $id",
            ex
        )
    }

    @Throws(FetchTrainingByIdRemoteException::class)
    override suspend fun getTrainingByIdList(idList: List<String>): List<T> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(collectionName)
                    .whereIn(ID_FIELD, idList)
                    .get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchTrainingByIdRemoteException(
            "An error occurred when trying to fetch trainings by ID list",
            ex
        )
    }

    @Throws(FetchTrainingByCategoryRemoteException::class)
    override suspend fun getTrainingByCategory(id: String): List<T> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(collectionName)
                    .whereEqualTo(CATEGORY_FIELD, id)
                    .get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchTrainingByCategoryRemoteException(
            "An error occurred when trying to fetch trainings by category",
            ex
        )
    }

    @Throws(FetchFeaturedTrainingsRemoteException::class)
    override suspend fun getFeaturedTrainings(): List<T> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(collectionName)
                    .whereEqualTo(IS_FEATURED_FIELD, true)
                    .get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchFeaturedTrainingsRemoteException(
            "An error occurred when trying to fetch featured trainings",
            ex
        )
    }

    @Throws(FetchRecommendedTrainingsRemoteException::class)
    override suspend fun getRecommendedTrainings(): List<T> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(collectionName)
                    .whereEqualTo(IS_RECOMMENDED_FIELD, true)
                    .get()
            },
            mapper = { data -> dataMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRecommendedTrainingsRemoteException(
            "An error occurred when trying to fetch recommended trainings",
            ex
        )
    }
}