package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.exception.InvalidDataException
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.fudge.core.FudgeUseCaseWithParams

class UpdateProfileUseCase(
    private val profilesRepository: IProfilesRepository,
    private val validator: IBusinessEntityValidator<UpdatedProfileRequestBO>
): FudgeUseCaseWithParams<UpdateProfileUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO = with(params) {
        toUpdatedProfileRequestBO().let { updatedProfileRequestBO ->
            validator.validate(updatedProfileRequestBO).takeIf { it.isNotEmpty() }?.let { errors ->
                throw InvalidDataException(errors, "Invalid data provided")
            } ?: run {
                profilesRepository.updateProfile(profileId, updatedProfileRequestBO)
            }
        }
    }

    private fun Params.toUpdatedProfileRequestBO() = UpdatedProfileRequestBO(
        alias = alias,
        avatarType = avatarType
    )

    data class Params(
        val profileId: String,
        val alias: String?,
        val avatarType: AvatarTypeEnum?
    )
}