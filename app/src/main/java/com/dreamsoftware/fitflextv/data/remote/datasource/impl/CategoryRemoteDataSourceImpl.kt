package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchCategoriesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchCategoryByIdRemoteException
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class CategoryRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val categoriesMapper: IOneSideMapper<Map<String, Any?>, CategoryDTO>,
    dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), ICategoryRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "categories"
    }

    @Throws(FetchCategoriesRemoteException::class)
    override suspend fun getCategories(): List<CategoryDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { categoriesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchCategoriesRemoteException("An error occurred when trying to fetch categories", ex)
    }

    @Throws(FetchCategoryByIdRemoteException::class)
    override suspend fun getCategoryById(id: String): CategoryDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get() },
            mapper = { categoriesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchCategoriesRemoteException("An error occurred when trying to fetch categories", ex)
    }
}