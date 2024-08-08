package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.TrainingSongBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingSongsRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetSongByIdUseCase(
    private val trainingSongRepository: ITrainingSongsRepository
) : BaseUseCaseWithParams<GetSongByIdUseCase.Params, TrainingSongBO>() {

    override suspend fun onExecuted(params: Params): TrainingSongBO =
        trainingSongRepository.getSongById(params.id)

    data class Params(
        val id: String
    )
}