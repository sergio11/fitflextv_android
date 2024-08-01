package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedWorkoutsExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByCategoryExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutsExceptionRemote
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineDispatcher

internal class WorkoutRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val workoutMapper: IOneSideMapper<Map<String, Any?>, WorkoutDTO>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), IWorkoutRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "workouts"
        const val CATEGORY_FIELD = "category"
        const val ID_FIELD = "uid"
        const val IS_FEATURED_FIELD = "isFeatured"
        const val LANGUAGE = "language"
        const val DURATION = "duration"
        const val INTENSITY = "intensity"
        const val RELEASED_DATE = "releasedDate"
        const val WORKOUT_TYPE = "workoutType"
    }

    @Throws(FetchRemoteWorkoutsExceptionRemote::class)
    override suspend fun getWorkouts(filter: TrainingFilterDTO): List<WorkoutDTO> = try {
        fetchListFromFireStore(
            query = {
                with(filter) {
                    var query: Query = firebaseStore.collection(COLLECTION_NAME)
                    classLanguage?.let { query = query.whereEqualTo(LANGUAGE, it) }
                    intensity?.let { query = query.whereEqualTo(INTENSITY, it) }
                    workoutType?.let { query = query.whereEqualTo(WORKOUT_TYPE, it) }
                    query.get()
                }
            },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteWorkoutsExceptionRemote(
            "An error occurred when trying to fetch workouts",
            ex
        )
    }

    @Throws(FetchRemoteWorkoutByIdExceptionRemote::class)
    override suspend fun getWorkoutById(id: String): WorkoutDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).document(id).get() },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteWorkoutByIdExceptionRemote(
            "An error occurred when trying to fetch the workout with ID $id",
            ex
        )
    }

    @Throws(FetchRemoteWorkoutByIdExceptionRemote::class)
    override suspend fun getWorkoutByIdList(idList: List<String>): List<WorkoutDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereIn(ID_FIELD, idList)
                    .get()
            },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteWorkoutByIdExceptionRemote(
            "An error occurred when trying to fetch workouts by ID list",
            ex
        )
    }

    @Throws(FetchRemoteWorkoutByCategoryExceptionRemote::class)
    override suspend fun getWorkoutByCategory(id: String): List<WorkoutDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(CATEGORY_FIELD, id)
                    .get()
            },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteWorkoutByCategoryExceptionRemote(
            "An error occurred when trying to fetch workouts by category",
            ex
        )
    }

    @Throws(FetchRemoteFeaturedWorkoutsExceptionRemote::class)
    override suspend fun getFeaturedWorkouts(): List<WorkoutDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(IS_FEATURED_FIELD, true)
                    .get()
            },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteFeaturedWorkoutsExceptionRemote(
            "An error occurred when trying to fetch featured workouts",
            ex
        )
    }
}