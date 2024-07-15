package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteCategoriesException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteCategoryByIdException

interface ICategoryRemoteDataSource {

    @Throws(FetchRemoteCategoriesException::class)
    suspend fun getCategories(): List<CategoryDTO>

    @Throws(FetchRemoteCategoryByIdException::class)
    suspend fun getCategoryById(id: String): CategoryDTO
}