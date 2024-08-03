package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class UserRemoteMapper: IOneSideMapper<Map<String, Any?>, UserResponseDTO> {

    private companion object {
        const val UUID_KEY = "uuid"
        const val USERNAME_KEY = "username"
        const val EMAIL_KEY = "email"
        const val FIRST_NAME_KEY = "firstName"
        const val LAST_NAME_KEY = "lastName"
        const val PROFILES_COUNT_KEY = "profilesCount"
    }

    override fun mapInToOut(input: Map<String, Any?>): UserResponseDTO = with(input) {
        UserResponseDTO(
            uuid = get(UUID_KEY) as String,
            username = get(USERNAME_KEY) as String,
            email = get(EMAIL_KEY) as String,
            firstName = get(FIRST_NAME_KEY) as String,
            lastName = get(LAST_NAME_KEY) as String,
            profilesCount = get(PROFILES_COUNT_KEY) as Long
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<UserResponseDTO> =
        input.map(::mapInToOut)
}