package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class CategoryMapper : IOneSideMapper<CategoryDTO, CategoryBO> {

    override fun mapInToOut(input: CategoryDTO): CategoryBO = with(input) {
        CategoryBO(
            id = id,
            title = title,
            description = description,
            imageUrl = imageUrl
        )
    }

    override fun mapInListToOutList(input: Iterable<CategoryDTO>): Iterable<CategoryBO> =
        input.map(::mapInToOut)
}