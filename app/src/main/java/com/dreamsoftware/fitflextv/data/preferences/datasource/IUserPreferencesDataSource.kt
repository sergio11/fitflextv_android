package com.dreamsoftware.fitflextv.data.preferences.datasource

import com.dreamsoftware.fitflextv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.fitflextv.data.preferences.exception.FetchUserPreferencesException
import com.dreamsoftware.fitflextv.data.preferences.exception.SaveUserPreferencesException

interface IUserPreferencesDataSource {

    @Throws(SaveUserPreferencesException::class)
    suspend fun save(data: UserPreferencesDTO)

    @Throws(FetchUserPreferencesException::class)
    suspend fun get(): UserPreferencesDTO
}