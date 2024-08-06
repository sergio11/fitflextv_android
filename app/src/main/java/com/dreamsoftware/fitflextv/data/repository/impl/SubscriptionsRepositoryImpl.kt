package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ISubscriptionsRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchCategoriesRemoteException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.FetchCategoriesException
import com.dreamsoftware.fitflextv.domain.exception.FetchSubscriptionsException
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class SubscriptionsRepositoryImpl(
    private val subscriptionsRemoteDataSource: ISubscriptionsRemoteDataSource,
    private val subscriptionMapper: IOneSideMapper<SubscriptionDTO, SubscriptionBO>,
    dispatcher: CoroutineDispatcher
): SupportRepositoryImpl(dispatcher), ISubscriptionsRepository {

    @Throws(FetchSubscriptionsException::class)
    override suspend fun getSubscriptions(): List<SubscriptionBO> = safeExecute {
        try {
            subscriptionsRemoteDataSource
                .getSubscriptions()
                .let(subscriptionMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchCategoriesRemoteException) {
            throw FetchCategoriesException("An error occurred when fetching subscriptions", ex)
        }
    }
}