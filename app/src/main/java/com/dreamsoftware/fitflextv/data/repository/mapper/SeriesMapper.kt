package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.LanguageEnum
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.dreamsoftware.fitflextv.utils.enumValueOfOrDefault

internal class SeriesMapper : IOneSideMapper<Pair<SeriesDTO, InstructorDTO>, SeriesBO> {

    override fun mapInToOut(input: Pair<SeriesDTO, InstructorDTO>): SeriesBO = with(input) {
        SeriesBO(
            id = first.id,
            name = first.name,
            description = first.description,
            instructorName = second.name,
            workoutType = enumValueOfOrDefault(first.workoutType, WorkoutTypeEnum.YOGA),
            imageUrl = first.imageUrl,
            numberOfWeeks = first.numberOfWeeks,
            numberOfClasses = first.numberOfClasses,
            duration = first.duration,
            videoUrl = first.videoUrl,
            intensity = enumValueOfOrDefault(first.intensity, IntensityEnum.EASY),
            releasedDate = first.releasedDate,
            song = first.song,
            isPremium = first.isPremium,
            language = enumValueOfOrDefault(first.language, LanguageEnum.ENGLISH)
        )
    }

    override fun mapInListToOutList(input: Iterable<Pair<SeriesDTO, InstructorDTO>>): Iterable<SeriesBO> =
        input.map(::mapInToOut)
}