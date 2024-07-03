package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.SessionBO
import com.dreamsoftware.fitflextv.domain.repository.ISessionRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetSessionsUseCase(
    private val sessionRepository: ISessionRepository
) : BaseUseCase<List<SessionBO>>() {
    override suspend fun onExecuted(): List<SessionBO> =
        sessionRepository.getSessions()
}