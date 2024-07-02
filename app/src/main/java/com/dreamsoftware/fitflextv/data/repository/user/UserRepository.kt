package com.dreamsoftware.fitflextv.data.repository.user

import com.dreamsoftware.fitflextv.domain.model.ProfileBO

interface UserRepository {
    suspend fun getUserProfiles(): List<ProfileBO>

}