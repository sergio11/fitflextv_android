package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class ProfileRemoteMapper : IOneSideMapper<Map<String, Any?>, ProfileDTO> {

    private companion object {
        const val UUID_KEY = "uuid"
        const val ALIAS_KEY = "alias"
        const val IS_ADMIN_KEY = "isAdmin"
        const val IS_SECURED_KEY = "isSecured"
        const val USER_ID_KEY = "userId"
        const val AVATAR_TYPE_KEY = "avatarType"
    }

    override fun mapInToOut(input: Map<String, Any?>): ProfileDTO = with(input) {
        ProfileDTO(
            uuid = get(UUID_KEY) as String,
            alias = get(ALIAS_KEY) as String,
            isAdmin = get(IS_ADMIN_KEY) as Boolean,
            isSecured = get(IS_SECURED_KEY) as Boolean,
            avatarType = get(AVATAR_TYPE_KEY) as String,
            userId = get(USER_ID_KEY) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<ProfileDTO> =
        input.map(::mapInToOut)
}