package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetUserDetailUseCase(
    private val userRepository: IUserRepository
): FudgeTvUseCase<UserDetailBO>() {
    override suspend fun onExecuted(): UserDetailBO =
        userRepository.getDetail()
}