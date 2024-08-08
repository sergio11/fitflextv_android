package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.TrainingSongDTO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class TrainingSongRemoteMapper: IOneSideMapper<Map<String, Any?>, TrainingSongDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val AUDIO_URL_KEY = "audioUrl"
        const val TITLE_KEY = "title"
        const val DESCRIPTION_KEY = "description"
        const val AUTHOR_KEY = "author"
        const val IMAGE_URL_KEY = "imageUrl"
    }

    override fun mapInToOut(input: Map<String, Any?>): TrainingSongDTO = with(input) {
        TrainingSongDTO(
            id = get(UID_KEY) as String,
            title = get(TITLE_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            audioUrl = get(AUDIO_URL_KEY) as String,
            author = get(AUTHOR_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<TrainingSongDTO> =
        input.map(::mapInToOut)
}