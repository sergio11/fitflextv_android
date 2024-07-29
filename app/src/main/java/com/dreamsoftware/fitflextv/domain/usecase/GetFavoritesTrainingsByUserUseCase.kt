package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetFavoritesTrainingsByUserUseCase(
    private val userRepository: IUserRepository,
    private val trainingRepository: ITrainingRepository
): BaseUseCase<List<ITrainingProgramBO>>() {

    override suspend fun onExecuted(): List<ITrainingProgramBO> =
        trainingRepository.getFavoritesTrainingsByUser(userRepository.getAuthenticatedUid())
}