package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.fitflextv.domain.model.InstructorBO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class InstructorMapper : IOneSideMapper<InstructorDTO, InstructorBO> {

    override fun mapInToOut(input: InstructorDTO): InstructorBO = with(input) {
        InstructorBO(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl
        )
    }

    override fun mapInListToOutList(input: Iterable<InstructorDTO>): Iterable<InstructorBO> =
        input.map(::mapInToOut)
}