package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class CreateUserRequestRemoteMapper: IOneSideMapper<CreateUserDTO, Map<String, Any?>> {

    private companion object {
        const val UUID_KEY = "uuid"
        const val EMAIL_KEY = "email"
        const val USERNAME_KEY = "username"
        const val FIRST_NAME_KEY = "firstName"
        const val LAST_NAME_KEY = "lastName"
    }

    override fun mapInToOut(input: CreateUserDTO): Map<String, Any?> = with(input) {
        mapOf(
            UUID_KEY to uid,
            EMAIL_KEY to email,
            USERNAME_KEY to username,
            FIRST_NAME_KEY to firstName,
            LAST_NAME_KEY to lastName
        )
    }

    override fun mapInListToOutList(input: Iterable<CreateUserDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}