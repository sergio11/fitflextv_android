package com.dreamsoftware.fitflextv.data.preferences.datasource.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dreamsoftware.fitflextv.data.preferences.datasource.IUserPreferencesDataSource
import com.dreamsoftware.fitflextv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.fitflextv.data.preferences.exception.FetchUserPreferencesLocalException
import com.dreamsoftware.fitflextv.data.preferences.exception.SaveUserPreferencesLocalException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

internal class UserPreferencesDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val moshi: Moshi
) : IUserPreferencesDataSource {

    private companion object {
        const val USER_PREFERENCES_KEY = "USER_PREFERENCES_KEY"
    }

    private val userPreferencesPreferenceAdapter by lazy {
        moshi.adapter(UserPreferencesDTO::class.java)
    }

    @Throws(SaveUserPreferencesLocalException::class)
    override suspend fun save(data: UserPreferencesDTO) {
        val dataStoreKey = stringPreferencesKey(USER_PREFERENCES_KEY)
        dataStore.edit { pref ->
            pref[dataStoreKey] = userPreferencesPreferenceAdapter.toJson(data)
        }
    }

    @Throws(FetchUserPreferencesLocalException::class)
    override suspend fun get(): UserPreferencesDTO {
        val dataStoreKey = stringPreferencesKey(USER_PREFERENCES_KEY)
        return dataStore.data
            .map { pref -> pref[dataStoreKey]?.let(userPreferencesPreferenceAdapter::fromJson) }
            .firstOrNull() ?: throw FetchUserPreferencesLocalException("user preferences not found")
    }
}