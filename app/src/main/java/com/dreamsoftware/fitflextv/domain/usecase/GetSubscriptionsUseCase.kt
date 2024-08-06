package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetSubscriptionsUseCase(
    private val subscriptionsRepository: ISubscriptionsRepository
) : BaseUseCase<List<SubscriptionBO>>() {
    override suspend fun onExecuted(): List<SubscriptionBO> =
        subscriptionsRepository.getSubscriptions()
}