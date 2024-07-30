package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class TrainingFilterDataMapper: IOneSideMapper<TrainingFilterDataBO, TrainingFilterDTO> {
    override fun mapInToOut(input: TrainingFilterDataBO): TrainingFilterDTO = with(input) {
        TrainingFilterDTO(
            classLanguage = classLanguage.name,
            classType = classType.name,
            difficulty = difficulty.name,
            videoLength = videoLength.name
        )
    }

    override fun mapInListToOutList(input: Iterable<TrainingFilterDataBO>): Iterable<TrainingFilterDTO> =
        input.map(::mapInToOut)
}