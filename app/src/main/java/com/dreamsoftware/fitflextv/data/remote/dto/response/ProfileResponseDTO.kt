package com.dreamsoftware.fitflextv.data.remote.dto.response

/**
 * Data class representing the response containing profile information.
 *
 * @property uuid The UUID of the profile.
 * @property alias The alias of the profile.
 * @property isAdmin Indicates whether the profile is an admin.
 * @property isSecured Indicates whether the profile has a secure PIN or not
 * @property avatarType The type of avatar for the profile.
 */
data class ProfileResponseDTO(
    val uuid: String,
    val alias: String,
    val isAdmin: Boolean,
    val isSecured: Boolean,
    val avatarType: String,
    val userId: String
)
