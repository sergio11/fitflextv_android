package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesException

interface ISeriesRemoteDataSource {

    @Throws(FetchRemoteSeriesException::class)
    suspend fun getSeries(): List<SeriesDTO>

    @Throws(FetchRemoteSeriesByIdException::class)
    suspend fun getSeriesById(id: String): SeriesDTO
}