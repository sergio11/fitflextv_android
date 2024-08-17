package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.exception.InvalidDataException
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams
import java.util.UUID

class CreateProfileUseCase(
    private val userRepository: IUserRepository,
    private val profilesRepository: IProfilesRepository,
    private val validator: IBusinessEntityValidator<CreateProfileRequestBO>
): FudgeTvUseCaseWithParams<CreateProfileUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean = with(params) {
        toCreateProfileRequestBO(userRepository.getAuthenticatedUid()).let { createProfileRequestBO ->
            validator.validate(createProfileRequestBO).takeIf { it.isNotEmpty() }?.let { errors ->
                throw InvalidDataException(errors, "Invalid data provided")
            } ?: run {
                profilesRepository.createProfile(createProfileRequestBO)
            }
        }
    }

    private fun Params.toCreateProfileRequestBO(userId: String) = CreateProfileRequestBO(
        uid = UUID.randomUUID().toString(),
        pin = pin,
        alias = alias,
        avatarType = avatarType ?: AvatarTypeEnum.BOY,
        userId = userId
    )

    data class Params(
        val alias: String,
        val pin: Int?,
        val avatarType: AvatarTypeEnum?
    )
}