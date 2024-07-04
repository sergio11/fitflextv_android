package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.AuthenticationBO
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class SignInUseCase() : BaseUseCaseWithParams<SignInUseCase.Params, AuthenticationBO>() {
    override suspend fun onExecuted(params: Params): AuthenticationBO =
       TODO("Not implemented yet")

    data class Params(
        val email: String,
        val password: String
    )
}