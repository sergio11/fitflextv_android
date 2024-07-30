package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedSeriesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByCategoryExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesExceptionRemote

interface ISeriesRemoteDataSource {

    @Throws(FetchRemoteSeriesExceptionRemote::class)
    suspend fun getSeries(filter: TrainingFilterDTO): List<SeriesDTO>

    @Throws(FetchRemoteSeriesByIdExceptionRemote::class)
    suspend fun getSeriesById(id: String): SeriesDTO

    @Throws(FetchRemoteSeriesByCategoryExceptionRemote::class)
    suspend fun getSeriesByCategory(categoryId: String): List<SeriesDTO>

    @Throws(FetchRemoteFeaturedSeriesExceptionRemote::class)
    suspend fun getFeaturedSeries(): List<SeriesDTO>
}