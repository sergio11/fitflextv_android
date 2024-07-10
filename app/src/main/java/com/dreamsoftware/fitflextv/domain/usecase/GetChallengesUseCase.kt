package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.repository.IChallengesRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetChallengesUseCase(
    private val challengesRepository: IChallengesRepository
) : BaseUseCase<List<ChallengeBO>>() {
    override suspend fun onExecuted():  List<ChallengeBO> =
        challengesRepository.getChallenges()
}