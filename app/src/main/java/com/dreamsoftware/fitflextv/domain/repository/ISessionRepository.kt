package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.SessionBO

interface ISessionRepository {

    suspend fun getSessions(): List<SessionBO>
    suspend fun getCategories(): List<CategoryBO>
}