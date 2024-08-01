package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.domain.model.ClassLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.ClassTypeEnum
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.fitflextv.domain.model.VideoLengthEnum
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class TrainingFilterDataMapper: IOneSideMapper<TrainingFilterDataBO, TrainingFilterDTO> {
    override fun mapInToOut(input: TrainingFilterDataBO): TrainingFilterDTO = with(input) {
        TrainingFilterDTO(
            classLanguage = classLanguage.takeIf { it != ClassLanguageEnum.NOT_SET }?.value,
            classType = classType.takeIf { it != ClassTypeEnum.NOT_SET }?.value,
            intensity = intensity.takeIf { it != IntensityEnum.NOT_SET }?.value,
            videoLength = videoLength.takeIf { it != VideoLengthEnum.NOT_SET }?.value
        )
    }

    override fun mapInListToOutList(input: Iterable<TrainingFilterDataBO>): Iterable<TrainingFilterDTO> =
        input.map(::mapInToOut)
}