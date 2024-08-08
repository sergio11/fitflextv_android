package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams
import java.util.UUID

class AddUserSubscriptionUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository
) : BaseUseCaseWithParams<AddUserSubscriptionUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean = with(params) {
        val userId = userRepository.getAuthenticatedUid()
        subscriptionsRepository.addUserSubscription(AddUserSubscriptionBO(
            id = UUID.randomUUID().toString(),
            subscriptionId = subscriptionId,
            userId = userId,
            validUntil = validUntil
        ))
    }

    data class Params(
        val subscriptionId: String,
        val validUntil: Long
    )
}