package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class SignInUseCase(
    private val userRepository: IUserRepository
) : BaseUseCaseWithParams<SignInUseCase.Params, UserDetailBO>() {

    override suspend fun onExecuted(params: Params): UserDetailBO = with(params) {
        userRepository.signIn(email, password)
    }

    data class Params(
        val email: String,
        val password: String
    )
}