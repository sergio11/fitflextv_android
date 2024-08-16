package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.exception.InvalidDataException
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams
import com.dreamsoftware.fitflextv.domain.validation.IBusinessEntityValidator

class ChangeSecurePinUseCase(
    private val profilesRepository: IProfilesRepository,
    private val validator: IBusinessEntityValidator<UpdatedProfileRequestBO>
): BaseUseCaseWithParams<ChangeSecurePinUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO = with(params) {
        with(profilesRepository) {
            verifyPin(profileId, currentSecurePin)
            toUpdatedProfileRequestBO().let { updatedProfileRequestBO ->
                validator.validate(updatedProfileRequestBO).takeIf { it.isNotEmpty() }?.let { errors ->
                    throw InvalidDataException(errors, "Invalid data provided")
                } ?: run {
                    updateProfile(profileId, updatedProfileRequestBO)
                }
            }
        }
    }

    private fun Params.toUpdatedProfileRequestBO() = UpdatedProfileRequestBO(pin = newSecurePin)

    data class Params(
        val profileId: String,
        val currentSecurePin: Int,
        val newSecurePin: Int
    )
}