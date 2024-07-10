package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.WorkoutDTO
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.LanguageEnum
import com.dreamsoftware.fitflextv.domain.model.SubtitleLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.dreamsoftware.fitflextv.utils.enumValueOfOrDefault

internal class WorkoutMapper : IOneSideMapper<WorkoutDTO, WorkoutBO> {

    override fun mapInToOut(input: WorkoutDTO): WorkoutBO = with(input) {
        WorkoutBO(
            id = id,
            name = name,
            description = description,
            instructorName = instructorName,
            workoutType = enumValueOfOrDefault(workoutType, WorkoutTypeEnum.YOGA),
            imageUrl = imageUrl,
            duration = duration,
            videoUrl = videoUrl,
            intensity = enumValueOfOrDefault(intensity, IntensityEnum.EASY),
            releasedDate = releasedDate,
            language = enumValueOfOrDefault(language, LanguageEnum.ENGLISH),
            subtitleLanguage = enumValueOfOrDefault(subtitleLanguage, SubtitleLanguageEnum.ENGLISH),
        )
    }

    override fun mapInListToOutList(input: Iterable<WorkoutDTO>): Iterable<WorkoutBO> =
        input.map(::mapInToOut)
}