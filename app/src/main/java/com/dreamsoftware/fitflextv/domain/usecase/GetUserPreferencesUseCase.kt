package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserPreferenceBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetUserPreferencesUseCase(
    private val userRepository: IUserRepository,
) : BaseUseCase<UserPreferenceBO>() {

    override suspend fun onExecuted(): UserPreferenceBO =
        userRepository.getUserPreferences()
}