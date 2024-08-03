package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import java.util.Date

internal class SeriesRemoteMapper: IOneSideMapper<Map<String, Any?>, SeriesDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val NAME_KEY = "name"
        const val DESCRIPTION_KEY = "description"
        const val INSTRUCTOR_NAME_KEY = "instructorName"
        const val WORKOUT_TYPE_KEY = "workoutType"
        const val IMAGE_URL_KEY = "imageUrl"
        const val NUMBER_OF_WEEKS = "numberOfWeeks"
        const val NUMBER_OF_CLASSES = "numberOfClasses"
        const val DURATION = "duration"
        const val VIDEO_URL_KEY = "videoUrl"
        const val INTENSITY_KEY = "intensity"
        const val RELEASED_DATE_KEY = "releasedDate"
        const val LANGUAGE_KEY = "language"
        const val CATEGORY_KEY = "category"
    }

    override fun mapInToOut(input: Map<String, Any?>): SeriesDTO = with(input) {
        SeriesDTO(
            id = get(UID_KEY) as String,
            name = get(NAME_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            instructorName = get(INSTRUCTOR_NAME_KEY) as String,
            workoutType = get(WORKOUT_TYPE_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
            numberOfWeeks = get(NUMBER_OF_WEEKS) as Long,
            numberOfClasses = get(NUMBER_OF_CLASSES) as Long,
            duration = get(DURATION) as String,
            videoUrl = get(VIDEO_URL_KEY) as String,
            intensity = get(INTENSITY_KEY) as String,
            releasedDate = Date(),
            language = get(LANGUAGE_KEY) as String,
            category = get(CATEGORY_KEY) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<SeriesDTO> =
        input.map(::mapInToOut)
}