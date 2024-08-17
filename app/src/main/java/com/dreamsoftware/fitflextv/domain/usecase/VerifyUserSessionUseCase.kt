package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeUseCase

class VerifyUserSessionUseCase(
    private val userRepository: IUserRepository
): FudgeUseCase<Boolean>() {

    override suspend fun onExecuted(): Boolean = runCatching { userRepository.hasSession() }
        .getOrDefault(false)
}