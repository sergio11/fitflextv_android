package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.repository.ICategoryRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetCategoryByIdUseCase(
    private val categoryRepository: ICategoryRepository
) : BaseUseCaseWithParams<GetCategoryByIdUseCase.Params, CategoryBO>() {

    override suspend fun onExecuted(params: Params): CategoryBO =
        categoryRepository.getCategoryById(params.id)

    data class Params(
        val id: String
    )
}