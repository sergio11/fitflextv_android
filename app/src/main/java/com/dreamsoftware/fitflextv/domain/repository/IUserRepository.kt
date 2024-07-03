package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.ProfileBO

interface IUserRepository {
    suspend fun getUserProfiles(): List<ProfileBO>
}