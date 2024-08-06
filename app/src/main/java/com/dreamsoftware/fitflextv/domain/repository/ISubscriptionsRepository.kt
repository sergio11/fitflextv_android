package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.AddUserSubscriptionException
import com.dreamsoftware.fitflextv.domain.exception.FetchSubscriptionsException
import com.dreamsoftware.fitflextv.domain.exception.RemoveUserSubscriptionException
import com.dreamsoftware.fitflextv.domain.exception.VerifyHasActiveSubscriptionException
import com.dreamsoftware.fitflextv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO

interface ISubscriptionsRepository {

    @Throws(FetchSubscriptionsException::class)
    suspend fun getSubscriptions(): List<SubscriptionBO>

    @Throws(VerifyHasActiveSubscriptionException::class)
    suspend fun hasActiveSubscription(userId: String): Boolean

    @Throws(AddUserSubscriptionException::class)
    suspend fun addUserSubscription(data: AddUserSubscriptionBO): Boolean

    @Throws(RemoveUserSubscriptionException::class)
    suspend fun removeUserSubscription(userId: String): Boolean
}