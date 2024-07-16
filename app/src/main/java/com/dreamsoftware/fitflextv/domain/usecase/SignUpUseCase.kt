package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.CreateUserBO
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class SignUpUseCase(
    private val userRepository: IUserRepository
) : BaseUseCaseWithParams<SignUpUseCase.Params, UserDetailBO>() {

    override suspend fun onExecuted(params: Params): UserDetailBO = with(params) {
        userRepository.signUp(toCreateUserBO())
    }

    private fun Params.toCreateUserBO() = CreateUserBO(
        username = username,
        password = password,
        email = email,
        firstName = firstName,
        lastName = lastName
    )

    data class Params(
        val username: String,
        val repeatPassword: String,
        val password: String,
        val email: String,
        val firstName: String,
        val lastName: String
    )
}