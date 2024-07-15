package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesByCategoryException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedChallengesException
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

    @Throws(FetchRemoteChallengesException::class)
    override suspend fun getChallenges(): List<ChallengeDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { data -> challengeMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteChallengesException("An error occurred when trying to fetch challenges", ex)
    }

    @Throws(FetchRemoteChallengesByIdException::class)
    override suspend fun getChallengeById(id: String): ChallengeDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).document(id).get() },
            mapper = { data -> challengeMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteChallengesByIdException("An error occurred when trying to fetch the challenge with ID $id", ex)
    }

    @Throws(FetchRemoteChallengesByCategoryException::class)
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
        throw FetchRemoteChallengesByCategoryException("An error occurred when trying to fetch challenges by category", ex)
    }

    @Throws(FetchRemoteFeaturedChallengesException::class)
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
        throw FetchRemoteFeaturedChallengesException("An error occurred when trying to fetch featured challenges", ex)
    }
}