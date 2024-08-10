package com.dreamsoftware.fitflextv.data.preferences.datasource

import com.dreamsoftware.fitflextv.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.fitflextv.data.preferences.exception.FetchProfileSelectedPreferenceLocalException
import com.dreamsoftware.fitflextv.data.preferences.exception.SaveProfileSelectedPreferenceLocalException
import kotlin.jvm.Throws

/**
 * An interface representing a data source for profile session-related operations.
 */
interface IProfileSessionDataSource {

    /**
     * Saves the selected profile preference.
     * @param profile The profile selected preference DTO to be saved.
     */
    @Throws(SaveProfileSelectedPreferenceLocalException::class)
    suspend fun save(profile: ProfileSelectedPreferenceDTO)

    /**
     * Retrieves the selected profile preference.
     * @return The retrieved profile selected preference DTO.
     */
    @Throws(FetchProfileSelectedPreferenceLocalException::class)
    suspend fun get(): ProfileSelectedPreferenceDTO
}