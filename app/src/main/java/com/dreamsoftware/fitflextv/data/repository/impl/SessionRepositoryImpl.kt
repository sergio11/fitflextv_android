package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ISessionRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.SessionDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSessionsException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.FetchSessionsException
import com.dreamsoftware.fitflextv.domain.model.SessionBO
import com.dreamsoftware.fitflextv.domain.repository.ISessionRepository
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class SessionRepositoryImpl(
    private val sessionRemoteDataSource: ISessionRemoteDataSource,
    private val sessionMapper: IOneSideMapper<SessionDTO, SessionBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ISessionRepository {

    @Throws(FetchSessionsException::class)
    override suspend fun getSessions(): List<SessionBO> = safeExecute {
        try {
            sessionRemoteDataSource
                .getSessions()
                .let(sessionMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchRemoteSessionsException) {
            throw FetchSessionsException("An error occurred when fetching sessions", ex)
        }
    }
}