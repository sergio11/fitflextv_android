package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.SeriesDTO
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.LanguageEnum
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.SubtitleLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.dreamsoftware.fitflextv.utils.enumValueOfOrDefault

internal class SeriesMapper : IOneSideMapper<SeriesDTO, SeriesBO> {

    override fun mapInToOut(input: SeriesDTO): SeriesBO = with(input) {
        SeriesBO(
            id = id,
            name = name,
            description = description,
            instructorName = instructorName,
            workoutTypeEnum = enumValueOfOrDefault(workoutType, WorkoutTypeEnum.YOGA),
            imageUrl = imageUrl,
            numberOfWeeks = numberOfWeeks,
            numberOfClasses = numberOfClasses,
            minutesPerDay = minutesPerDay,
            videoUrl = videoUrl,
            intensityEnum = enumValueOfOrDefault(intensity, IntensityEnum.EASY),
            releasedDate = releasedDate,
            language = enumValueOfOrDefault(language, LanguageEnum.ENGLISH),
            subtitleLanguage = enumValueOfOrDefault(subtitleLanguage, SubtitleLanguageEnum.ENGLISH)
        )
    }

    override fun mapInListToOutList(input: Iterable<SeriesDTO>): Iterable<SeriesBO> =
        input.map(::mapInToOut)
}