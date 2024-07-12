package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteCategoriesException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.FetchCategoriesException
import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.repository.ICategoryRepository
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class CategoryRepositoryImpl(
    private val categoryRemoteDataSource: ICategoryRemoteDataSource,
    private val categoryMapper: IOneSideMapper<CategoryDTO, CategoryBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ICategoryRepository {

    @Throws(FetchCategoriesException::class)
    override suspend fun getCategories(): List<CategoryBO> = safeExecute {
        try {
            categoryRemoteDataSource
                .getCategories()
                .let(categoryMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchRemoteCategoriesException) {
            throw FetchCategoriesException("An error occurred when fetching categories", ex)
        }
    }
}