package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.repository.ICategoryRepository
import com.dreamsoftware.fudge.core.FudgeUseCase

class GetCategoriesUseCase(
    private val categoryRepository: ICategoryRepository
) : FudgeUseCase<List<CategoryBO>>() {
    override suspend fun onExecuted(): List<CategoryBO> =
        categoryRepository.getCategories()
}