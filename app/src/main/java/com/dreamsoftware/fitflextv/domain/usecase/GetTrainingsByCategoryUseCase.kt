package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetTrainingsByCategoryUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository,
    private val trainingRepository: ITrainingRepository
) : BaseUseCaseWithParams<GetTrainingsByCategoryUseCase.Params, List<ITrainingProgramBO>>() {
    override suspend fun onExecuted(params: Params): List<ITrainingProgramBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val hasActiveSubscription = subscriptionsRepository.hasActiveSubscription(userUid)
        return trainingRepository.getTrainingsByCategory(
            id = params.id,
            includePremium = hasActiveSubscription
        ).toList()
    }

    data class Params(
        val id: String
    )
}