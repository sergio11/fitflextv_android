package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchSeriesByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchSeriesException
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.SongBO
import kotlin.jvm.Throws

interface ISeriesRepository {

    @Throws(FetchSeriesException::class)
    suspend fun getSeries(): List<SeriesBO>

    @Throws(FetchSeriesByIdException::class)
    suspend fun getSeriesById(id: String): SeriesBO

    fun getSongById(id: String): SongBO
}