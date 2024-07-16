package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class SignOffUseCase(
    private val userRepository: IUserRepository,
): BaseUseCase<Unit>() {
    override suspend fun onExecuted() {
        userRepository.signOff()
    }
}