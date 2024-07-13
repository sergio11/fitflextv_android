package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchCategoriesException
import com.dreamsoftware.fitflextv.domain.exception.FetchCategoryByIdException
import com.dreamsoftware.fitflextv.domain.model.CategoryBO

interface ICategoryRepository {

    @Throws(FetchCategoriesException::class)
    suspend fun getCategories(): List<CategoryBO>

    @Throws(FetchCategoryByIdException::class)
    suspend fun getCategoryById(id: String): CategoryBO
}