package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetProfileByIdUseCase(
    private val profilesRepository: IProfilesRepository
): BaseUseCaseWithParams<GetProfileByIdUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO =
        profilesRepository.getProfileById(params.profileId)

    data class Params(
        val profileId: String
    )
}