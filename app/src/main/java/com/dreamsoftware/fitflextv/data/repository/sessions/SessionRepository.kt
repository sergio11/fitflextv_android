package com.dreamsoftware.fitflextv.data.repository.sessions

import com.dreamsoftware.fitflextv.data.entities.Category
import com.dreamsoftware.fitflextv.data.entities.Session

interface SessionRepository {

    suspend fun getSessions(): List<Session>
    suspend fun getCategories(): List<Category>
}