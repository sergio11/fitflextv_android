package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ISubscriptionsRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchSubscriptionsRemoteException
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class SubscriptionsRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val subscriptionsMapper: IOneSideMapper<Map<String, Any?>, SubscriptionDTO>,
    dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), ISubscriptionsRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "subscriptions"
    }

    @Throws(FetchSubscriptionsRemoteException::class)
    override suspend fun getSubscriptions(): List<SubscriptionDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { subscriptionsMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchSubscriptionsRemoteException("An error occurred when trying to fetch subscriptions", ex)
    }
}