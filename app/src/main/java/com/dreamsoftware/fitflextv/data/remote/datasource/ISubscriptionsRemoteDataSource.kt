package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchSubscriptionsRemoteException

interface ISubscriptionsRemoteDataSource {

    @Throws(FetchSubscriptionsRemoteException::class)
    suspend fun getSubscriptions(): List<SubscriptionDTO>
}