package com.dreamsoftware.fitflextv.data.repository.user

import com.dreamsoftware.fitflextv.data.entities.Profile

interface UserRepository {
    suspend fun getUserProfiles(): List<Profile>

}