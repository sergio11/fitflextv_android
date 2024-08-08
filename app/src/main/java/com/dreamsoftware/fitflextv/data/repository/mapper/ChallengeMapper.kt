package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.model.ChallengeWeaklyPlansBO
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.LanguageEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.dreamsoftware.fitflextv.utils.enumValueOfOrDefault

internal class ChallengeMapper(
    private val workoutMapper: IOneSideMapper<WorkoutDTO, WorkoutBO>
) : IOneSideMapper<Pair<ChallengeDTO, List<WorkoutDTO>>, ChallengeBO> {

    override fun mapInToOut(input: Pair<ChallengeDTO, List<WorkoutDTO>>): ChallengeBO = with(input) {
        ChallengeBO(
            id = first.id,
            name = first.name,
            description = first.description,
            instructorName = first.instructorName,
            workoutType = enumValueOfOrDefault(first.workoutType, WorkoutTypeEnum.YOGA),
            imageUrl = first.imageUrl,
            duration = first.duration,
            videoUrl = first.videoUrl,
            intensity = enumValueOfOrDefault(first.intensity, IntensityEnum.EASY),
            releasedDate = first.releasedDate,
            language = enumValueOfOrDefault(first.language, LanguageEnum.ENGLISH),
            numberOfDays = first.numberOfDays,
            song = first.song,
            weaklyPlans = first.weaklyPlans.map { weaklyPlan ->
                ChallengeWeaklyPlansBO(
                    name = weaklyPlan.name,
                    workouts = weaklyPlan.workouts.mapNotNull {  workout ->
                        second.find { it.id == workout }
                    }.map(workoutMapper::mapInToOut)
                )
            }
        )
    }

    override fun mapInListToOutList(input: Iterable<Pair<ChallengeDTO, List<WorkoutDTO>>>): Iterable<ChallengeBO> =
        input.map(::mapInToOut)
}