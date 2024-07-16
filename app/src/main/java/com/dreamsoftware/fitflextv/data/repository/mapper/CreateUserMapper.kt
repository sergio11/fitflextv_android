package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.domain.model.CreateUserBO
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class CreateUserMapper : IOneSideMapper<CreateUserBO, CreateUserDTO> {

    override fun mapInToOut(input: CreateUserBO): CreateUserDTO = with(input) {
        CreateUserDTO(
            uid = String.EMPTY,
            email = email,
            username = username,
            firstName = firstName,
            lastName = lastName
        )
    }

    override fun mapInListToOutList(input: Iterable<CreateUserBO>): Iterable<CreateUserDTO> =
        input.map(::mapInToOut)
}