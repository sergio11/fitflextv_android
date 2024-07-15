package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class UpdateProfileRequestRemoteMapper: IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>> {

    private companion object {
        const val ALIAS_KEY = "alias"
        const val PIN_KEY = "pin"
        const val AVATAR_KEY = "avatar_key"
    }

    override fun mapInToOut(input: UpdatedProfileRequestDTO): Map<String, Any?> = with(input) {
        mutableMapOf<String, Any?>().apply {
            alias?.let { put(ALIAS_KEY, it) }
            pin?.let { put(PIN_KEY, it) }
            avatarType?.let { put(AVATAR_KEY, it) }
        }
    }

    override fun mapInListToOutList(input: Iterable<UpdatedProfileRequestDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}