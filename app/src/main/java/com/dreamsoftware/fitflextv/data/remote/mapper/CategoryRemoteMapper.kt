package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class CategoryRemoteMapper: IOneSideMapper<Map<String, Any?>, CategoryDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val TITLE_KEY = "title"
        const val DESCRIPTION_KEY = "description"
        const val IMAGE_URL_KEY = "imageUrl"
    }

    override fun mapInToOut(input: Map<String, Any?>): CategoryDTO = with(input) {
        CategoryDTO(
            id = get(UID_KEY) as String,
            title = get(TITLE_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<CategoryDTO> =
        input.map(::mapInToOut)
}