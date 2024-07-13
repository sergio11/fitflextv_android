package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByCategoryException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class SeriesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val seriesMapper: IOneSideMapper<Map<String, Any?>, SeriesDTO>,
    dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), ISeriesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "series"
        const val CATEGORY_FIELD = "category"
    }

    @Throws(FetchRemoteSeriesException::class)
    override suspend fun getSeries(): List<SeriesDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { seriesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteSeriesException("An error occurred when trying to fetch series", ex)
    }

    @Throws(FetchRemoteSeriesByIdException::class)
    override suspend fun getSeriesById(id: String): SeriesDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get() },
            mapper = { seriesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteSeriesByIdException("An error occurred when trying to fetch the series with ID $id", ex)
    }

    @Throws(FetchRemoteSeriesByCategoryException::class)
    override suspend fun getSeriesByCategory(categoryId: String): List<SeriesDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(CATEGORY_FIELD, categoryId)
                    .get()
            },
            mapper = { data -> seriesMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteSeriesByCategoryException("An error occurred when trying to fetch series by category", ex)
    }
}