package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetFeaturedTrainingsUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository,
    private val trainingRepository: ITrainingRepository
) : BaseUseCase<List<ITrainingProgramBO>>() {
    override suspend fun onExecuted(): List<ITrainingProgramBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val hasActiveSubscription = subscriptionsRepository.hasActiveSubscription(userUid)
        return trainingRepository.getFeaturedTrainings(includePremium = hasActiveSubscription).toList()
    }
}