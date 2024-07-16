package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetUserDetailUseCase(
    private val userRepository: IUserRepository
): BaseUseCase<UserDetailBO>() {
    override suspend fun onExecuted(): UserDetailBO =
        userRepository.getDetail()
}