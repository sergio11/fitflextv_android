package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class CreateProfileRequestRemoteMapper: IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>> {

    private companion object {
        const val UUID_KEY = "uuid"
        const val ALIAS_KEY = "alias"
        const val PIN_KEY = "pin"
        const val AVATAR_KEY = "avatar_key"
        const val USER_ID = "userId"
    }

    override fun mapInToOut(input: CreateProfileRequestDTO): Map<String, Any?> = with(input) {
        mapOf(
            UUID_KEY to uid,
            ALIAS_KEY to alias,
            PIN_KEY to pin,
            AVATAR_KEY to avatarType,
            USER_ID to userId
        )
    }

    override fun mapInListToOutList(input: Iterable<CreateProfileRequestDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}