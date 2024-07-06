package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteCategoriesException

interface ICategoryRemoteDataSource {

    @Throws(FetchRemoteCategoriesException::class)
    suspend fun getCategories(): List<CategoryDTO>
}