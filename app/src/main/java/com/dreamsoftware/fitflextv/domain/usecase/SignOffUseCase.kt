package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeUseCase

class SignOffUseCase(
    private val userRepository: IUserRepository,
): FudgeUseCase<Unit>() {
    override suspend fun onExecuted() {
        userRepository.signOff()
    }
}