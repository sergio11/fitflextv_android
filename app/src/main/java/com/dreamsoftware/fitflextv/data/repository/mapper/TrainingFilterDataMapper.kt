package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.domain.model.ClassLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.SortTypeEnum
import com.dreamsoftware.fitflextv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.fitflextv.domain.model.VideoLengthEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class TrainingFilterDataMapper: IOneSideMapper<TrainingFilterDataBO, TrainingFilterDTO> {
    override fun mapInToOut(input: TrainingFilterDataBO): TrainingFilterDTO = with(input) {
        TrainingFilterDTO(
            classLanguage = classLanguage.takeIf { it != ClassLanguageEnum.NOT_SET }?.value,
            workoutType = workoutType.takeIf { it != WorkoutTypeEnum.NOT_SET }?.value,
            intensity = intensity.takeIf { it != IntensityEnum.NOT_SET }?.value,
            videoLength = videoLength.takeIf { it != VideoLengthEnum.NOT_SET }?.range,
            orderByReleasedDateDesc = sortType == SortTypeEnum.NEWEST || sortType == SortTypeEnum.NOT_SET,
            priorityFeatured = sortType == SortTypeEnum.RELEVANCE
        )
    }

    override fun mapInListToOutList(input: Iterable<TrainingFilterDataBO>): Iterable<TrainingFilterDTO> =
        input.map(::mapInToOut)
}