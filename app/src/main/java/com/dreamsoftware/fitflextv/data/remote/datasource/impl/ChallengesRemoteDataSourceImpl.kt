package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesByCategoryExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedChallengesExceptionRemote
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
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
    }

    @Throws(FetchRemoteChallengesExceptionRemote::class)
    override suspend fun getChallenges(filter: TrainingFilterDTO): List<ChallengeDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { data -> challengeMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteChallengesExceptionRemote("An error occurred when trying to fetch challenges", ex)
    }

    @Throws(FetchRemoteChallengesByIdExceptionRemote::class)
    override suspend fun getChallengeById(id: String): ChallengeDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).document(id).get() },
            mapper = { data -> challengeMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteChallengesByIdExceptionRemote("An error occurred when trying to fetch the challenge with ID $id", ex)
    }

    @Throws(FetchRemoteChallengesByCategoryExceptionRemote::class)
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
        throw FetchRemoteChallengesByCategoryExceptionRemote("An error occurred when trying to fetch challenges by category", ex)
    }

    @Throws(FetchRemoteFeaturedChallengesExceptionRemote::class)
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
        throw FetchRemoteFeaturedChallengesExceptionRemote("An error occurred when trying to fetch featured challenges", ex)
    }
}