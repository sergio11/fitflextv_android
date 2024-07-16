package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class SignInUseCase() : BaseUseCaseWithParams<SignInUseCase.Params, UserDetailBO>() {
    override suspend fun onExecuted(params: Params): UserDetailBO =
       TODO("Not implemented yet")

    data class Params(
        val email: String,
        val password: String
    )
}