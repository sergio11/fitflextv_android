package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserPreferenceBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeUseCase

class GetUserPreferencesUseCase(
    private val userRepository: IUserRepository,
) : FudgeUseCase<UserPreferenceBO>() {

    override suspend fun onExecuted(): UserPreferenceBO =
        userRepository.getUserPreferences()
}