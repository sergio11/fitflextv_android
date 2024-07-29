package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.fitflextv.domain.model.AddFavoriteTrainingBO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class AddFavoriteTrainingMapper : IOneSideMapper<AddFavoriteTrainingBO, AddFavoriteTrainingDTO> {

    override fun mapInToOut(input: AddFavoriteTrainingBO): AddFavoriteTrainingDTO = with(input) {
        AddFavoriteTrainingDTO(
            userId = userId,
            trainingId = trainingId,
            trainingType = trainingType.name
        )
    }

    override fun mapInListToOutList(input: Iterable<AddFavoriteTrainingBO>): Iterable<AddFavoriteTrainingDTO> =
        input.map(::mapInToOut)
}