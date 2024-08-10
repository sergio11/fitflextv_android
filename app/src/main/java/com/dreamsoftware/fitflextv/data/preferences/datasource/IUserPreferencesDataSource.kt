package com.dreamsoftware.fitflextv.data.preferences.datasource

import com.dreamsoftware.fitflextv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.fitflextv.data.preferences.exception.FetchUserPreferencesLocalException
import com.dreamsoftware.fitflextv.data.preferences.exception.SaveUserPreferencesLocalException

interface IUserPreferencesDataSource {

    @Throws(SaveUserPreferencesLocalException::class)
    suspend fun save(data: UserPreferencesDTO)

    @Throws(FetchUserPreferencesLocalException::class)
    suspend fun get(): UserPreferencesDTO
}