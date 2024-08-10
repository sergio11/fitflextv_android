package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchChallengesByCategoryRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchChallengesByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchChallengesRecommendedRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchChallengesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchFeaturedChallengesRemoteException
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineDispatcher

internal class ChallengesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val challengeMapper: IOneSideMapper<Map<String, Any?>, ChallengeDTO>,
    dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IChallengesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "challenges"
        const val CATEGORY_FIELD = "category"
        const val IS_FEATURED_FIELD = "isFeatured"
        const val IS_RECOMMENDED_FIELD = "isRecommended"
        const val LANGUAGE = "language"
        const val DURATION = "duration"
        const val INTENSITY = "intensity"
        const val RELEASED_DATE = "releasedDate"
        const val WORKOUT_TYPE = "workoutType"
        const val INSTRUCTOR = "instructor"
    }

    @Throws(FetchChallengesRemoteException::class)
    override suspend fun getChallenges(filter: TrainingFilterDTO): List<ChallengeDTO> = try {
        fetchListFromFireStore(
            query = {
                with(filter) {
                    var query: Query = firebaseStore.collection(COLLECTION_NAME)
                    classLanguage?.let { query = query.whereEqualTo(LANGUAGE, it) }
                    intensity?.let { query = query.whereEqualTo(INTENSITY, it) }
                    workoutType?.let { query = query.whereEqualTo(WORKOUT_TYPE, it) }
                    instructor?.let { query = query.whereEqualTo(INSTRUCTOR, it) }
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
            mapper = { data -> challengeMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchChallengesRemoteException("An error occurred when trying to fetch challenges", ex)
    }

    @Throws(FetchChallengesByIdRemoteException::class)
    override suspend fun getChallengeById(id: String): ChallengeDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).document(id).get() },
            mapper = { data -> challengeMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchChallengesByIdRemoteException("An error occurred when trying to fetch the challenge with ID $id", ex)
    }

    @Throws(FetchChallengesByCategoryRemoteException::class)
    override suspend fun getChallengesByCategory(categoryId: String): List<ChallengeDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(CATEGORY_FIELD, categoryId)
                    .get()
            },
            mapper = { data -> challengeMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchChallengesByCategoryRemoteException("An error occurred when trying to fetch challenges by category", ex)
    }

    @Throws(FetchFeaturedChallengesRemoteException::class)
    override suspend fun getFeaturedChallenges(): List<ChallengeDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(IS_FEATURED_FIELD, true)
                    .get()
            },
            mapper = { data -> challengeMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchFeaturedChallengesRemoteException("An error occurred when trying to fetch featured challenges", ex)
    }

    @Throws(FetchChallengesRecommendedRemoteException::class)
    override suspend fun getRecommendedChallenges(): List<ChallengeDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(IS_RECOMMENDED_FIELD, true)
                    .get()
            },
            mapper = { data -> challengeMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchChallengesRecommendedRemoteException(
            "An error occurred when trying to fetch recommended challenges",
            ex
        )
    }
}