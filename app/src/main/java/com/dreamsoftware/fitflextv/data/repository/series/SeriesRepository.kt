package com.dreamsoftware.fitflextv.data.repository.series

import com.dreamsoftware.fitflextv.data.entities.Series
import com.dreamsoftware.fitflextv.data.entities.Song

interface SeriesRepository {
    fun getSeries(): List<Series>
    fun getSeriesById(id: String): Series
    fun getSongById(id: String): Song
}