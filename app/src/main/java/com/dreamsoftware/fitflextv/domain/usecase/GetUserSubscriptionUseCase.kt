package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserSubscriptionBO
import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetUserSubscriptionUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository
) : FudgeTvUseCase<UserSubscriptionBO>() {

    override suspend fun onExecuted(): UserSubscriptionBO {
        val userId = userRepository.getAuthenticatedUid()
        return subscriptionsRepository.getUserSubscription(userId)
    }
}