package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeWeaklyPlansDTO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import java.util.Date

@Suppress("UNCHECKED_CAST")
internal class ChallengeRemoteMapper: IOneSideMapper<Map<String, Any?>, ChallengeDTO> {

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
        const val RELEASE_DATE_KEY = "releasedDate"
        const val SONG_KEY = "songId"
        const val LANGUAGE_KEY = "language"
        const val CATEGORY_KEY = "category"
        const val NUMBER_OF_DAYS_KEY = "numberOfDays"
        const val WEAKLY_PLANS_KEY = "weaklyPlans"
    }

    override fun mapInToOut(input: Map<String, Any?>): ChallengeDTO = with(input) {
        ChallengeDTO(
            id = get(UID_KEY) as String,
            name = get(NAME_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            instructorName = get(INSTRUCTOR_NAME_KEY) as String,
            workoutType = get(WORKOUT_TYPE_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
            duration = get(DURATION_KEY) as String,
            videoUrl = get(VIDEO_URL_KEY) as String,
            intensity = get(INTENSITY_KEY) as String,
            releasedDate = Date(),
            language = get(LANGUAGE_KEY) as String,
            category = get(CATEGORY_KEY) as String,
            song = get(SONG_KEY) as String,
            numberOfDays = get(NUMBER_OF_DAYS_KEY) as Long,
            weaklyPlans = mapWeaklyPlans(get(WEAKLY_PLANS_KEY) as Map<String, List<String>>)
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<ChallengeDTO> =
        input.map(::mapInToOut)

    private fun mapWeaklyPlans(weaklyPlans: Map<String, List<String>>): List<ChallengeWeaklyPlansDTO> = weaklyPlans.map {
        ChallengeWeaklyPlansDTO(
            name = it.key,
            workouts = it.value
        )
    }
}