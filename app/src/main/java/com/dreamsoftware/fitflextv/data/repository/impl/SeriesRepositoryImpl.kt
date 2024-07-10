package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSeriesException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.FetchSeriesByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchSeriesException
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.SongBO
import com.dreamsoftware.fitflextv.domain.repository.ISeriesRepository
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.jvm.Throws

internal class SeriesRepositoryImpl(
    private val seriesRemoteDataSource: ISeriesRemoteDataSource,
    private val seriesMapper: IOneSideMapper<SeriesDTO, SeriesBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ISeriesRepository {

    @Throws(FetchSeriesException::class)
    override suspend fun getSeries(): List<SeriesBO> = safeExecute {
        try {
            seriesRemoteDataSource
                .getSeries()
                .let(seriesMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchRemoteSeriesException) {
            throw FetchSeriesException("An error occurred when fetching series", ex)
        }
    }

    @Throws(FetchSeriesByIdException::class)
    override suspend fun getSeriesById(id: String): SeriesBO = safeExecute {
        try {
            seriesRemoteDataSource
                .getSeriesById(id)
                .let(seriesMapper::mapInToOut)
        } catch (ex: FetchRemoteSeriesByIdException) {
            throw FetchSeriesByIdException("An error occurred when fetching series $id", ex)
        }
    }

    override fun getSongById(id: String): SongBO {
        return SongBO(
            id = "123456sdasdsa",
            title = "Power Workout",
            author = "Jake Diaz",
            createdDate = "2021",
            audioUrl = "",
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/7927e3f0-bb09-4309-a11c-0748cd39b535",
        )
    }
}