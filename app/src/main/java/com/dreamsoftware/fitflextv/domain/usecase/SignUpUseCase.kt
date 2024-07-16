package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class SignUpUseCase() : BaseUseCaseWithParams<SignUpUseCase.Params, UserDetailBO>() {
    override suspend fun onExecuted(params: Params): UserDetailBO =
       TODO("Not implemented yet")

    data class Params(
        val username: String,
        val repeatPassword: String,
        val password: String,
        val email: String,
        val firstName: String,
        val lastName: String
    )
}