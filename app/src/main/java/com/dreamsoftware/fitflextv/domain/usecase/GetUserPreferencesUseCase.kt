package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserPreferenceBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetUserPreferencesUseCase(
    private val userRepository: IUserRepository,
) : FudgeTvUseCase<UserPreferenceBO>() {

    override suspend fun onExecuted(): UserPreferenceBO =
        userRepository.getUserPreferences()
}