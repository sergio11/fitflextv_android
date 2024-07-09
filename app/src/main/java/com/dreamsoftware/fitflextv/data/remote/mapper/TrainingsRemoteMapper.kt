package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.TrainingDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class TrainingsRemoteMapper: IOneSideMapper<Map<String, Any?>, TrainingDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val TYPE_KEY = "type"
        const val TITLE_KEY = "title"
        const val INSTRUCTOR_KEY = "instructor"
        const val DESCRIPTION_KEY = "description"
        const val TIME_KEY = "time"
        const val IMAGE_URL_KEY = "imageUrl"
    }

    override fun mapInToOut(input: Map<String, Any?>): TrainingDTO = with(input) {
        TrainingDTO(
            id = get(UID_KEY) as String,
            title = get(TITLE_KEY) as String,
            type = get(TYPE_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            instructor = get(INSTRUCTOR_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
            time = get(TIME_KEY) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<TrainingDTO> =
        input.map(::mapInToOut)
}