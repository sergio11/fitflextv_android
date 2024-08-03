package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class CreateProfileRequestMapper :
    IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO> {

    override fun mapInToOut(input: CreateProfileRequestBO): CreateProfileRequestDTO = with(input) {
        CreateProfileRequestDTO(
            uid = uid,
            alias = alias,
            pin = pin,
            avatarType = avatarType.name,
            userId = userId
        )
    }

    override fun mapInListToOutList(input: Iterable<CreateProfileRequestBO>): Iterable<CreateProfileRequestDTO> =
        input.map(::mapInToOut)
}