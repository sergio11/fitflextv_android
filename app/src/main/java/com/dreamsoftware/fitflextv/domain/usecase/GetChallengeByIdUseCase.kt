package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.repository.IChallengesRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetChallengeByIdUseCase(
        private val challengesRepository: IChallengesRepository
) : BaseUseCaseWithParams<GetChallengeByIdUseCase.Params, ChallengeBO>() {
    override suspend fun onExecuted(params: Params): ChallengeBO =
        challengesRepository.getChallengeById(params.id)

    data class Params(
        val id: String
    )
}