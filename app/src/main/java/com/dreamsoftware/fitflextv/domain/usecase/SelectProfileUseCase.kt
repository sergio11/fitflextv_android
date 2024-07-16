package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class SelectProfileUseCase(
    private val profilesRepository: IProfilesRepository
): BaseUseCaseWithParams<SelectProfileUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params): Unit =
        profilesRepository.selectProfile(params.profile)

    data class Params(
        val profile: ProfileBO
    )
}