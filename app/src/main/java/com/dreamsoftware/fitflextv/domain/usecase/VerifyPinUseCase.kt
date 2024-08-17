package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fudge.core.FudgeUseCaseWithParams

class VerifyPinUseCase(
    private val profilesRepository: IProfilesRepository
): FudgeUseCaseWithParams<VerifyPinUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        profilesRepository.verifyPin(profileId, pin)
    }

    data class Params(
        val profileId: String,
        val pin: Int
    )
}