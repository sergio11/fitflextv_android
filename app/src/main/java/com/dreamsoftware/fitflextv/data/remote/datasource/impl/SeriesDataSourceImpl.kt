package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesException
import com.dreamsoftware.fitflextv.data.remote.exception.FirebaseException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class SeriesDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val seriesMapper: IOneSideMapper<Map<String, Any?>, SeriesDTO>,
    private val dispatcher: CoroutineDispatcher
): ISeriesDataSource {

    private companion object {
        const val COLLECTION_NAME = "series"
    }

    @Throws(FetchRemoteSeriesException::class)
    override suspend fun getSeries(): List<SeriesDTO> = withContext(dispatcher)  {
        try {
            val snapshot = firebaseStore.collection(COLLECTION_NAME)
                .get()
                .await()
            snapshot.documents.map { document ->
                seriesMapper.mapInToOut(
                    document.data ?: throw FetchRemoteSeriesException("series data is null")
                )
            }
        } catch (ex: FirebaseException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteSeriesException(
                "An error occurred when trying to fetch series",
                ex
            )
        }
    }

    @Throws(FetchRemoteSeriesByIdException::class)
    override suspend fun getSeriesById(id: String): SeriesDTO = withContext(dispatcher)  {
        try {
            val document = firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .await()
            seriesMapper.mapInToOut(
                document.data ?: throw FetchRemoteSeriesByIdException("series data is null")
            )
        } catch (ex: FirebaseException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteSeriesByIdException(
                "An error occurred when trying to fetch the series with ID $id",
                ex
            )
        }
    }
}