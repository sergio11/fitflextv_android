package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.domain.model.SignUpBO
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class CreateUserMapper : IOneSideMapper<SignUpBO, CreateUserDTO> {

    override fun mapInToOut(input: SignUpBO): CreateUserDTO = with(input) {
        CreateUserDTO(
            uid = String.EMPTY,
            email = email,
            username = username,
            firstName = firstName,
            lastName = lastName
        )
    }

    override fun mapInListToOutList(input: Iterable<SignUpBO>): Iterable<CreateUserDTO> =
        input.map(::mapInToOut)
}