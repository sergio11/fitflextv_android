package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class HasActiveSubscriptionUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository
): BaseUseCase<Boolean>() {
    override suspend fun onExecuted(): Boolean {
        val userUid = userRepository.getAuthenticatedUid()
        return subscriptionsRepository.hasActiveSubscription(userUid)
    }
}