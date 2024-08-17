package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class SignOffUseCase(
    private val userRepository: IUserRepository,
): FudgeTvUseCase<Unit>() {
    override suspend fun onExecuted() {
        userRepository.signOff()
    }
}