package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class UpdatedUserRequestMapper : IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO> {

    override fun mapInToOut(input: UpdatedUserRequestBO): UpdatedUserRequestDTO = with(input) {
        UpdatedUserRequestDTO(
            firstName = firstName,
            lastName = lastName,
            username = username
        )
    }

    override fun mapInListToOutList(input: Iterable<UpdatedUserRequestBO>): Iterable<UpdatedUserRequestDTO> =
        input.map(::mapInToOut)
}