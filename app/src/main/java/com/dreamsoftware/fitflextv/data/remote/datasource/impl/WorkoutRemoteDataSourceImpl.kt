package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedWorkoutsException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByCategoryException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutsException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class WorkoutRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val workoutMapper: IOneSideMapper<Map<String, Any?>, WorkoutDTO>,
    dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IWorkoutRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "workouts"
        const val CATEGORY_FIELD = "category"
        const val ID_FIELD = "uid"
        const val IS_FEATURED_FIELD = "isFeatured"
    }

    @Throws(FetchRemoteWorkoutsException::class)
    override suspend fun getWorkouts(): List<WorkoutDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteWorkoutsException("An error occurred when trying to fetch workouts", ex)
    }

    @Throws(FetchRemoteWorkoutByIdException::class)
    override suspend fun getWorkoutById(id: String): WorkoutDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).document(id).get() },
            mapper = { data -> workoutMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteWorkoutByIdException("An error occurred when trying to fetch the workout with ID $id", ex)
    }

    @Throws(FetchRemoteWorkoutByIdException::class)
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
        throw FetchRemoteWorkoutByIdException("An error occurred when trying to fetch workouts by ID list", ex)
    }

    @Throws(FetchRemoteWorkoutByCategoryException::class)
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
        throw FetchRemoteWorkoutByCategoryException("An error occurred when trying to fetch workouts by category", ex)
    }

    @Throws(FetchRemoteFeaturedWorkoutsException::class)
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
        throw FetchRemoteFeaturedWorkoutsException("An error occurred when trying to fetch featured workouts", ex)
    }
}