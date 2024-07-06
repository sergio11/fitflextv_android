package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.LanguageEnum
import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.model.SubtitleLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.dreamsoftware.fitflextv.utils.enumValueOfOrDefault

internal class RoutineMapper : IOneSideMapper<RoutineDTO, RoutineBO> {

    override fun mapInToOut(input: RoutineDTO): RoutineBO = with(input) {
        RoutineBO(
            id = id,
            name = name,
            description = description,
            instructorName = instructorName,
            workoutTypeEnum = enumValueOfOrDefault(workoutType, WorkoutTypeEnum.YOGA),
            imageUrl = imageUrl,
            duration = duration,
            videoUrl = videoUrl,
            intensityEnum = enumValueOfOrDefault(intensity, IntensityEnum.EASY),
            releasedDate = releasedDate,
            language = enumValueOfOrDefault(language, LanguageEnum.ENGLISH),
            subtitleLanguage = enumValueOfOrDefault(subtitleLanguage, SubtitleLanguageEnum.ENGLISH)
        )
    }

    override fun mapInListToOutList(input: Iterable<RoutineDTO>): Iterable<RoutineBO> =
        input.map(::mapInToOut)
}