package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.exception.InvalidDataException
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.fitflextv.domain.model.SignUpBO
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams
import com.dreamsoftware.fitflextv.domain.validation.IBusinessEntityValidator
import java.util.UUID

class SignUpUseCase(
    private val userRepository: IUserRepository,
    private val profilesRepository: IProfilesRepository,
    private val validator: IBusinessEntityValidator<SignUpBO>
) : BaseUseCaseWithParams<SignUpUseCase.Params, UserDetailBO>() {

    private companion object {
        const val DEFAULT_PIN = 123456
    }

    override suspend fun onExecuted(params: Params): UserDetailBO = with(params) {
        params.toSignUpBO().let { signUpBO ->
            validator.validate(signUpBO).takeIf { it.isNotEmpty() }?.let { errors ->
                throw InvalidDataException(errors, "Invalid data provided")
            } ?: run {
                userRepository.signUp(signUpBO).also {
                    profilesRepository.createProfile(toCreateProfileBO(it.uuid))
                }
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


    private fun Params.toCreateProfileBO(userId: String) = CreateProfileRequestBO(
        uid = UUID.randomUUID().toString(),
        alias = username,
        pin = DEFAULT_PIN,
        avatarType = AvatarTypeEnum.BOY,
        userId = userId
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