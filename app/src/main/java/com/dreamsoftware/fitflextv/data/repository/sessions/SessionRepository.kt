package com.dreamsoftware.fitflextv.data.repository.sessions

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.SessionBO

interface SessionRepository {

    suspend fun getSessions(): List<SessionBO>
    suspend fun getCategories(): List<CategoryBO>
}