package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeUseCase

class RemoveUserSubscriptionUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository
) : FudgeUseCase<Boolean>() {

    override suspend fun onExecuted(): Boolean {
        val userId = userRepository.getAuthenticatedUid()
        return subscriptionsRepository.removeUserSubscription(userId)
    }
}