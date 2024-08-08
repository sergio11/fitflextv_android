package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.TrainingSongDTO
import com.dreamsoftware.fitflextv.domain.model.TrainingSongBO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class TrainingSongMapper : IOneSideMapper<TrainingSongDTO, TrainingSongBO> {

    override fun mapInToOut(input: TrainingSongDTO): TrainingSongBO = with(input) {
        TrainingSongBO(
            id = id,
            title = title,
            description = description,
            imageUrl = imageUrl,
            audioUrl = audioUrl,
            author = author
        )
    }

    override fun mapInListToOutList(input: Iterable<TrainingSongDTO>): Iterable<TrainingSongBO> =
        input.map(::mapInToOut)
}