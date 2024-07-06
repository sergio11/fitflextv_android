package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.SessionDTO
import com.dreamsoftware.fitflextv.domain.model.SessionBO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class SessionMapper : IOneSideMapper<SessionDTO, SessionBO> {

    override fun mapInToOut(input: SessionDTO): SessionBO = with(input) {
        SessionBO(
            id = id,
            instructor = instructor,
            title = title,
            description = description,
            imageUrl = imageUrl
        )
    }

    override fun mapInListToOutList(input: Iterable<SessionDTO>): Iterable<SessionBO> =
        input.map(::mapInToOut)
}