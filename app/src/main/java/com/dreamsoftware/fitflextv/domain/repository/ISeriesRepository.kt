package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.SongBO

interface ISeriesRepository {
    fun getSeries(): List<SeriesBO>
    fun getSeriesById(id: String): SeriesBO
    fun getSongById(id: String): SongBO
}