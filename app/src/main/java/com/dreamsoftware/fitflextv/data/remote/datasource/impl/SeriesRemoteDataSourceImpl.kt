package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedSeriesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByCategoryExceptionRemote
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineDispatcher

internal class SeriesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val seriesMapper: IOneSideMapper<Map<String, Any?>, SeriesDTO>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), ISeriesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "series"
        const val CATEGORY_FIELD = "category"
        const val IS_FEATURED_FIELD = "isFeatured"
        const val LANGUAGE = "language"
        const val DURATION = "duration"
        const val INTENSITY = "intensity"
        const val RELEASED_DATE = "releasedDate"
        const val WORKOUT_TYPE = "workoutType"
    }

    @Throws(FetchRemoteSeriesExceptionRemote::class)
    override suspend fun getSeries(filter: TrainingFilterDTO): List<SeriesDTO> = try {
        fetchListFromFireStore(
            query = {
                with(filter) {
                    var query: Query = firebaseStore.collection(COLLECTION_NAME)
                    classLanguage?.let { query = query.whereEqualTo(LANGUAGE, it) }
                    intensity?.let { query = query.whereEqualTo(INTENSITY, it) }
                    workoutType?.let { query = query.whereEqualTo(WORKOUT_TYPE, it) }
                    videoLength?.let {
                        query = query
                            .whereGreaterThanOrEqualTo(DURATION, it.first)
                            .whereLessThanOrEqualTo(DURATION, it.last)
                    }
                    // Apply sorting
                    if (priorityFeatured) {
                        query = query.orderBy(IS_FEATURED_FIELD, Query.Direction.DESCENDING)
                    }
                    query = if (orderByReleasedDateDesc) {
                        query.orderBy(RELEASED_DATE, Query.Direction.DESCENDING)
                    } else {
                        query.orderBy(RELEASED_DATE, Query.Direction.ASCENDING)
                    }
                    query.get()
                }
            },
            mapper = { seriesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteSeriesExceptionRemote("An error occurred when trying to fetch series", ex)
    }

    @Throws(FetchRemoteSeriesByIdExceptionRemote::class)
    override suspend fun getSeriesById(id: String): SeriesDTO = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(id)
                    .get()
            },
            mapper = { seriesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteSeriesByIdExceptionRemote(
            "An error occurred when trying to fetch the series with ID $id",
            ex
        )
    }

    @Throws(FetchRemoteSeriesByCategoryExceptionRemote::class)
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
        throw FetchRemoteSeriesByCategoryExceptionRemote(
            "An error occurred when trying to fetch series by category",
            ex
        )
    }

    @Throws(FetchRemoteFeaturedSeriesExceptionRemote::class)
    override suspend fun getFeaturedSeries(): List<SeriesDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(IS_FEATURED_FIELD, true)
                    .get()
            },
            mapper = { data -> seriesMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteSeriesByCategoryExceptionRemote(
            "An error occurred when trying to fetch featured series",
            ex
        )
    }
}