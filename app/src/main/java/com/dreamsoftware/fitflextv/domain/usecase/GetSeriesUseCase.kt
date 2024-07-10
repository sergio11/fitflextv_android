package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.repository.ISeriesRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetSeriesUseCase(
    private val seriesRepository: ISeriesRepository
) : BaseUseCase<List<SeriesBO>>() {
    override suspend fun onExecuted():  List<SeriesBO> =
        seriesRepository.getSeries()
}