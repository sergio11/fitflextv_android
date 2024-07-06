package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ICategoryDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteCategoriesException
import com.dreamsoftware.fitflextv.data.remote.exception.FirebaseException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class CategoryDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val categoriesMapper: IOneSideMapper<Map<String, Any?>, CategoryDTO>,
    private val dispatcher: CoroutineDispatcher
): ICategoryDataSource {

    private companion object {
        const val COLLECTION_NAME = "categories"
    }

    @Throws(FetchRemoteCategoriesException::class)
    override suspend fun getCategories(): List<CategoryDTO> = withContext(dispatcher)  {
        try {
            val snapshot = firebaseStore.collection(COLLECTION_NAME)
                .get()
                .await()
            snapshot.documents.map { document ->
                categoriesMapper.mapInToOut(
                    document.data ?: throw FetchRemoteCategoriesException("categories data is null")
                )
            }
        } catch (ex: FirebaseException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteCategoriesException(
                "An error occurred when trying to fetch categories",
                ex
            )
        }
    }
}