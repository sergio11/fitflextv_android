package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.FavoriteTrainingDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class FavoriteTrainingRemoteMapper: IOneSideMapper<Map<String, Any?>, FavoriteTrainingDTO> {

    private companion object {
        const val TRAINING_ID = "trainingId"
        const val TRAINING_TYPE = "trainingType"
    }

    override fun mapInToOut(input: Map<String, Any?>): FavoriteTrainingDTO = with(input) {
        FavoriteTrainingDTO(
            trainingId = get(TRAINING_ID) as String,
            trainingType = get(TRAINING_TYPE) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<FavoriteTrainingDTO> =
        input.map(::mapInToOut)
}