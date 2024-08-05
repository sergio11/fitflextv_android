package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchCategoriesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchCategoryByIdRemoteException

interface ICategoryRemoteDataSource {

    @Throws(FetchCategoriesRemoteException::class)
    suspend fun getCategories(): List<CategoryDTO>

    @Throws(FetchCategoryByIdRemoteException::class)
    suspend fun getCategoryById(id: String): CategoryDTO
}