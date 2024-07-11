package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.repository.ISeriesRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetSeriesByIdUseCase(
    private val seriesRepository: ISeriesRepository
) : BaseUseCaseWithParams<GetSeriesByIdUseCase.Params, SeriesBO>() {

    override suspend fun onExecuted(params: Params): SeriesBO =
        seriesRepository.getSeriesById(params.id)

    data class Params(
        val id: String
    )
}