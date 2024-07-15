package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class UpdateProfileRequestMapper : IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO> {

    override fun mapInToOut(input: UpdatedProfileRequestBO): UpdatedProfileRequestDTO = with(input) {
        UpdatedProfileRequestDTO(
            alias = alias,
            pin = pin,
            avatarType = avatarType
        )
    }

    override fun mapInListToOutList(input: Iterable<UpdatedProfileRequestBO>): Iterable<UpdatedProfileRequestDTO> =
        input.map(::mapInToOut)
}