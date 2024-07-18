package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class UserDetailMapper : IOneSideMapper<UserResponseDTO, UserDetailBO> {

    override fun mapInToOut(input: UserResponseDTO): UserDetailBO = with(input) {
        UserDetailBO(
            uuid = uuid,
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName,
            profilesCount = profilesCount
        )
    }

    override fun mapInListToOutList(input: Iterable<UserResponseDTO>): Iterable<UserDetailBO> =
        input.map(::mapInToOut)
}