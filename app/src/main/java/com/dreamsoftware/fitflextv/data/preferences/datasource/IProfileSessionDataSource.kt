package com.dreamsoftware.fitflextv.data.preferences.datasource

import com.dreamsoftware.fitflextv.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.fitflextv.data.preferences.exception.FetchProfileSelectedPreferenceException
import com.dreamsoftware.fitflextv.data.preferences.exception.SaveProfileSelectedPreferenceException
import kotlin.jvm.Throws

/**
 * An interface representing a data source for profile session-related operations.
 */
interface IProfileSessionDataSource {

    /**
     * Saves the selected profile preference.
     * @param profile The profile selected preference DTO to be saved.
     */
    @Throws(SaveProfileSelectedPreferenceException::class)
    suspend fun save(profile: ProfileSelectedPreferenceDTO)

    /**
     * Retrieves the selected profile preference.
     * @return The retrieved profile selected preference DTO.
     */
    @Throws(FetchProfileSelectedPreferenceException::class)
    suspend fun get(): ProfileSelectedPreferenceDTO
}