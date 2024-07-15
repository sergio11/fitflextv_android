package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class UpdatedUserRequestRemoteMapper: IOneSideMapper<UpdatedUserRequestDTO, Map<String, Any?>> {

    private companion object {
        const val USERNAME_KEY = "username"
        const val FIRST_NAME_KEY = "firstName"
        const val LAST_NAME_KEY = "lastName"
    }

    override fun mapInToOut(input: UpdatedUserRequestDTO): Map<String, Any?> = with(input) {
        mutableMapOf<String, Any?>().apply {
            firstName?.let { put(FIRST_NAME_KEY, it) }
            lastName?.let { put(LAST_NAME_KEY, it) }
            username?.let { put(USERNAME_KEY, it) }
        }
    }

    override fun mapInListToOutList(input: Iterable<UpdatedUserRequestDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}