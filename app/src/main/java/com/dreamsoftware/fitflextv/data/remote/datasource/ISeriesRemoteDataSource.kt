package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchFeaturedSeriesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchSeriesByCategoryRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchSeriesByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchSeriesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchSeriesRecommendedRemoteException

interface ISeriesRemoteDataSource {

    @Throws(FetchSeriesRemoteException::class)
    suspend fun getSeries(filter: TrainingFilterDTO): List<SeriesDTO>

    @Throws(FetchSeriesByIdRemoteException::class)
    suspend fun getSeriesById(id: String): SeriesDTO

    @Throws(FetchSeriesByCategoryRemoteException::class)
    suspend fun getSeriesByCategory(categoryId: String): List<SeriesDTO>

    @Throws(FetchFeaturedSeriesRemoteException::class)
    suspend fun getFeaturedSeries(): List<SeriesDTO>

    @Throws(FetchSeriesRecommendedRemoteException::class)
    suspend fun getRecommendedSeries(): List<SeriesDTO>
}