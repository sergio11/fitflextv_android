package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteCategoriesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteCategoryByIdExceptionRemote

interface ICategoryRemoteDataSource {

    @Throws(FetchRemoteCategoriesExceptionRemote::class)
    suspend fun getCategories(): List<CategoryDTO>

    @Throws(FetchRemoteCategoryByIdExceptionRemote::class)
    suspend fun getCategoryById(id: String): CategoryDTO
}