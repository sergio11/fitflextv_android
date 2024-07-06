package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchSessionsException
import com.dreamsoftware.fitflextv.domain.model.SessionBO
import kotlin.jvm.Throws

interface ISessionRepository {

    @Throws(FetchSessionsException::class)
    suspend fun getSessions(): List<SessionBO>
}