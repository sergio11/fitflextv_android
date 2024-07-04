package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.SongBO
import com.dreamsoftware.fitflextv.domain.repository.ISeriesRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetSongByIdUseCase(
    private val seriesRepository: ISeriesRepository
) : BaseUseCaseWithParams<GetSongByIdUseCase.Params, SongBO>() {
    override suspend fun onExecuted(params: Params): SongBO =
        seriesRepository.getSongById(params.id)

    data class Params(
        val id: String
    )
}