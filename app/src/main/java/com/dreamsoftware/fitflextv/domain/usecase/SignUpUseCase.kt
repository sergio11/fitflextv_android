package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.exception.InvalidDataException
import com.dreamsoftware.fitflextv.domain.model.SignUpBO
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams
import com.dreamsoftware.fitflextv.domain.validation.IBusinessEntityValidator

class SignUpUseCase(
    private val userRepository: IUserRepository,
    private val validator: IBusinessEntityValidator<SignUpBO>
) : BaseUseCaseWithParams<SignUpUseCase.Params, UserDetailBO>() {

    override suspend fun onExecuted(params: Params): UserDetailBO = with(params) {
        params.toSignUpBO().let { signUpBO ->
            validator.validate(signUpBO).takeIf { it.isNotEmpty() }?.let { errors ->
                throw InvalidDataException(errors, "Invalid data provided")
            } ?: run {
                userRepository.signUp(signUpBO)
            }
        }
    }

    private fun Params.toSignUpBO() = SignUpBO(
        username = username,
        password = password,
        confirmPassword = repeatPassword,
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