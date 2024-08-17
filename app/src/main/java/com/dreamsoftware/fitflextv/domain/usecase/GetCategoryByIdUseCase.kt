package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.repository.ICategoryRepository
import com.dreamsoftware.fudge.core.FudgeUseCaseWithParams

class GetCategoryByIdUseCase(
    private val categoryRepository: ICategoryRepository
) : FudgeUseCaseWithParams<GetCategoryByIdUseCase.Params, CategoryBO>() {

    override suspend fun onExecuted(params: Params): CategoryBO =
        categoryRepository.getCategoryById(params.id)

    data class Params(
        val id: String
    )
}