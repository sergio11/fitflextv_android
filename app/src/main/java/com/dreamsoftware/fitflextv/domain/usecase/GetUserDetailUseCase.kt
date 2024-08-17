package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeUseCase

class GetUserDetailUseCase(
    private val userRepository: IUserRepository
): FudgeUseCase<UserDetailBO>() {
    override suspend fun onExecuted(): UserDetailBO =
        userRepository.getDetail()
}