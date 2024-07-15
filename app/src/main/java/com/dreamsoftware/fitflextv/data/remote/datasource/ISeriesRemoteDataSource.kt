package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedSeriesException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByCategoryException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesException

interface ISeriesRemoteDataSource {

    @Throws(FetchRemoteSeriesException::class)
    suspend fun getSeries(): List<SeriesDTO>

    @Throws(FetchRemoteSeriesByIdException::class)
    suspend fun getSeriesById(id: String): SeriesDTO

    @Throws(FetchRemoteSeriesByCategoryException::class)
    suspend fun getSeriesByCategory(categoryId: String): List<SeriesDTO>

    @Throws(FetchRemoteFeaturedSeriesException::class)
    suspend fun getFeaturedSeries(): List<SeriesDTO>
}