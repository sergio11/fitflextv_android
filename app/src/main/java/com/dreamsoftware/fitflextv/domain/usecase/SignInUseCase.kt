package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.exception.InvalidDataException
import com.dreamsoftware.fitflextv.domain.model.SignInBO
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.fudge.core.FudgeUseCaseWithParams

/**
 * SignIn Use Case
 * @param userRepository
 * @param validator
 */
class SignInUseCase(
    private val userRepository: IUserRepository,
    private val validator: IBusinessEntityValidator<SignInBO>
) : FudgeUseCaseWithParams<SignInUseCase.Params, UserDetailBO>() {

    override suspend fun onExecuted(params: Params): UserDetailBO = with(params) {
        params.toSignInBO().let { signInBO ->
            validator.validate(signInBO).takeIf { it.isNotEmpty() }?.let { errors ->
                throw InvalidDataException(errors, "Invalid data provided")
            } ?: run {
                userRepository.signIn(signInBO)
            }
        }
    }

    private fun Params.toSignInBO() =
        SignInBO(
            email = email,
            password = password
        )

    data class Params(
        val email: String,
        val password: String
    )
}