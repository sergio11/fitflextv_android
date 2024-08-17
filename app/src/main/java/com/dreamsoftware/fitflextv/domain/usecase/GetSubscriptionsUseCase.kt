package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetSubscriptionsUseCase(
    private val subscriptionsRepository: ISubscriptionsRepository
) : FudgeTvUseCase<List<SubscriptionBO>>() {
    override suspend fun onExecuted(): List<SubscriptionBO> =
        subscriptionsRepository.getSubscriptions()
}