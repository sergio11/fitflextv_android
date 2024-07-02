package com.dreamsoftware.fitflextv.data.repository.series

import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.SongBO

interface SeriesRepository {
    fun getSeries(): List<SeriesBO>
    fun getSeriesById(id: String): SeriesBO
    fun getSongById(id: String): SongBO
}