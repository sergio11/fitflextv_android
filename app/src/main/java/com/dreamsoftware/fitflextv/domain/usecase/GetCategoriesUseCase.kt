package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.repository.ISessionRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetCategoriesUseCase(
    private val sessionRepository: ISessionRepository
) : BaseUseCase<List<CategoryBO>>() {
    override suspend fun onExecuted(): List<CategoryBO> =
        sessionRepository.getCategories()
}