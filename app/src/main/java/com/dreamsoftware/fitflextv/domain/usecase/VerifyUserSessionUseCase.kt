package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class VerifyUserSessionUseCase(
    private val userRepository: IUserRepository
): BaseUseCase<Boolean>() {

    override suspend fun onExecuted(): Boolean = runCatching { userRepository.hasSession() }
        .getOrDefault(false)
}