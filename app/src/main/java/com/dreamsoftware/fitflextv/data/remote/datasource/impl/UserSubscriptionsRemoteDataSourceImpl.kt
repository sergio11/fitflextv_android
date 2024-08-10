package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IUserSubscriptionsRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AddUserSubscriptionRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchUserSubscriptionRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.RemoveUserSubscriptionRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.VerifyHasActiveSubscriptionRemoteException
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class UserSubscriptionsRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val userSubscriptionsMapper: IOneSideMapper<Map<String, Any?>, UserSubscriptionDTO>,
    private val addSubscriptionMapper: IOneSideMapper<AddUserSubscriptionDTO, Map<String, Any?>>,
    private val dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IUserSubscriptionsRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "user_subscriptions"
        const val VALID_UNTIL_KEY = "validUntil"
    }

    @Throws(FetchUserSubscriptionRemoteException::class)
    override suspend fun getUserSubscription(userId: String): UserSubscriptionDTO = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore
                    .collection(COLLECTION_NAME)
                    .document(userId)
                    .get()
            },
            mapper = { data -> userSubscriptionsMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchUserSubscriptionRemoteException("An error occurred when trying to fetch the user subscription", ex)
    }

    @Throws(VerifyHasActiveSubscriptionRemoteException::class)
    override suspend fun hasActiveSubscription(userId: String): Boolean = try {
        val document = firebaseStore
            .collection(COLLECTION_NAME)
            .document(userId)
            .get()
            .await()
        document.takeIf { it.exists() }?.getTimestamp(VALID_UNTIL_KEY)?.let { validUntil ->
            validUntil.toDate().time > System.currentTimeMillis()
        } ?: false
    } catch (ex: Exception) {
        throw VerifyHasActiveSubscriptionRemoteException(
            "An error occurred when trying to verify active subscription for user $userId",
            ex
        )
    }

    @Throws(AddUserSubscriptionRemoteException::class)
    override suspend fun addUserSubscription(data: AddUserSubscriptionDTO): Boolean = try {
        withContext(dispatcher) {
            firebaseStore.collection(COLLECTION_NAME)
                .document(data.userId)
                .set(addSubscriptionMapper.mapInToOut(data), SetOptions.merge())
                .await()
            true
        }
    } catch (ex: Exception) {
        throw AddUserSubscriptionRemoteException(
            "An error occurred when trying to add user subscription",
            ex
        )
    }

    @Throws(RemoveUserSubscriptionRemoteException::class)
    override suspend fun removeUserSubscription(userId: String): Boolean = try {
        withContext(dispatcher) {
            firebaseStore
                .collection(COLLECTION_NAME)
                .document(userId)
                .delete()
                .await()
            true
        }
    } catch (ex: Exception) {
        throw RemoveUserSubscriptionRemoteException(
            "An error occurred when trying to remove user subscription",
            ex
        )
    }
}