package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchSubscriptionsException
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO

interface ISubscriptionsRepository {

    @Throws(FetchSubscriptionsException::class)
    suspend fun getSubscriptions(): List<SubscriptionBO>
}