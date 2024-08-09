package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class InstructorRemoteMapper: IOneSideMapper<Map<String, Any?>, InstructorDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val NAME_KEY = "name"
        const val DESCRIPTION_KEY = "description"
        const val IMAGE_URL_KEY = "imageUrl"
    }

    override fun mapInToOut(input: Map<String, Any?>): InstructorDTO = with(input) {
        InstructorDTO(
            id = get(UID_KEY) as String,
            name = get(NAME_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<InstructorDTO> =
        input.map(::mapInToOut)
}