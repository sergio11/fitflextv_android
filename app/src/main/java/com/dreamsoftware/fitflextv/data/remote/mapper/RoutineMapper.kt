package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import java.util.Date

internal class RoutineMapper: IOneSideMapper<Map<String, Any?>, RoutineDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val NAME_KEY = "name"
        const val DESCRIPTION_KEY = "description"
        const val INSTRUCTOR_NAME_KEY = "instructorName"
        const val WORKOUT_TYPE_KEY = "workoutType"
        const val IMAGE_URL_KEY = "imageUrl"
        const val DURATION_KEY = "duration"
        const val VIDEO_URL_KEY = "videoUrl"
        const val INTENSITY_KEY = "intensity"
        const val RELEASED_DATE_KEY = "releasedDate"
        const val LANGUAGE_KEY = "language"
        const val SUBTITLE_LANGUAGE_KEY = "subtitleLanguage"
    }

    override fun mapInToOut(input: Map<String, Any?>): RoutineDTO = with(input) {
        RoutineDTO(
            id = get(UID_KEY) as String,
            name = get(NAME_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            instructorName = get(INSTRUCTOR_NAME_KEY) as String,
            workoutType = get(WORKOUT_TYPE_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
            duration = get(DURATION_KEY) as Long,
            videoUrl = get(VIDEO_URL_KEY) as String,
            intensity = get(INTENSITY_KEY) as String,
            releasedDate = Date(),
            language = get(LANGUAGE_KEY) as String,
            subtitleLanguage = get(SUBTITLE_LANGUAGE_KEY) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<RoutineDTO> =
        input.map(::mapInToOut)
}