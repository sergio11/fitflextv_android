package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchCategoriesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchCategoryByIdRemoteException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.FetchCategoriesException
import com.dreamsoftware.fitflextv.domain.exception.FetchCategoryByIdException
import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.repository.ICategoryRepository
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
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
        } catch (ex: FetchCategoriesRemoteException) {
            throw FetchCategoriesException("An error occurred when fetching categories", ex)
        }
    }

    @Throws(FetchCategoryByIdException::class)
    override suspend fun getCategoryById(id: String): CategoryBO = safeExecute {
        try {
            categoryRemoteDataSource
                .getCategoryById(id)
                .let(categoryMapper::mapInToOut)
        } catch (ex: FetchCategoryByIdRemoteException) {
            throw FetchCategoryByIdException("An error occurred when fetching category by $id", ex)
        }
    }
}