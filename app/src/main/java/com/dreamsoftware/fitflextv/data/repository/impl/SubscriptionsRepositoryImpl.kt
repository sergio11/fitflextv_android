package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ISubscriptionsRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IUserSubscriptionsRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AddUserSubscriptionRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchSubscriptionsRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.RemoveUserSubscriptionRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.VerifyHasActiveSubscriptionRemoteException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.AddUserSubscriptionException
import com.dreamsoftware.fitflextv.domain.exception.FetchSubscriptionsException
import com.dreamsoftware.fitflextv.domain.exception.RemoveUserSubscriptionException
import com.dreamsoftware.fitflextv.domain.exception.VerifyHasActiveSubscriptionException
import com.dreamsoftware.fitflextv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class SubscriptionsRepositoryImpl(
    private val userSubscriptionRemoteDataSource: IUserSubscriptionsRemoteDataSource,
    private val subscriptionsRemoteDataSource: ISubscriptionsRemoteDataSource,
    private val subscriptionMapper: IOneSideMapper<SubscriptionDTO, SubscriptionBO>,
    private val addUserSubscriptionMapper: IOneSideMapper<AddUserSubscriptionBO, AddUserSubscriptionDTO>,
    dispatcher: CoroutineDispatcher
): SupportRepositoryImpl(dispatcher), ISubscriptionsRepository {

    @Throws(FetchSubscriptionsException::class)
    override suspend fun getSubscriptions(): List<SubscriptionBO> = safeExecute {
        try {
            subscriptionsRemoteDataSource
                .getSubscriptions()
                .let(subscriptionMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchSubscriptionsRemoteException) {
            throw FetchSubscriptionsException("An error occurred when fetching subscriptions", ex)
        }
    }

    @Throws(VerifyHasActiveSubscriptionException::class)
    override suspend fun hasActiveSubscription(userId: String): Boolean = safeExecute {
        try {
            userSubscriptionRemoteDataSource.hasActiveSubscription(userId)
        } catch (ex: VerifyHasActiveSubscriptionRemoteException) {
            throw VerifyHasActiveSubscriptionException("An error occurred when checking if user has active subscription", ex)
        }
    }

    @Throws(AddUserSubscriptionException::class)
    override suspend fun addUserSubscription(data: AddUserSubscriptionBO): Boolean = safeExecute {
        try {
            userSubscriptionRemoteDataSource.addUserSubscription(addUserSubscriptionMapper.mapInToOut(data))
        } catch (ex: AddUserSubscriptionRemoteException) {
            throw AddUserSubscriptionException("An error occurred when adding user subscription", ex)
        }
    }

    @Throws(RemoveUserSubscriptionException::class)
    override suspend fun removeUserSubscription(userId: String): Boolean = safeExecute {
        try {
            userSubscriptionRemoteDataSource.removeUserSubscription(userId)
        } catch (ex: RemoveUserSubscriptionRemoteException) {
            throw RemoveUserSubscriptionException("An error occurred when removing user subscription", ex)
        }
    }
}