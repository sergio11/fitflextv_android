package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.TrainingDTO
import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class TrainingMapper : IOneSideMapper<TrainingDTO, TrainingBO> {

    override fun mapInToOut(input: TrainingDTO): TrainingBO = with(input) {
        TrainingBO(
            id = id,
            instructor = instructor,
            type = type,
            title = title,
            time = time,
            imageUrl = imageUrl,
            description = description
        )
    }

    override fun mapInListToOutList(input: Iterable<TrainingDTO>): Iterable<TrainingBO> =
        input.map(::mapInToOut)
}