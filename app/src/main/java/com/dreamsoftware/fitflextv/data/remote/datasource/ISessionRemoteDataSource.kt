package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.SessionDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSessionsException

interface ISessionRemoteDataSource {

    @Throws(FetchRemoteSessionsException::class)
    suspend fun getSessions(): List<SessionDTO>
}