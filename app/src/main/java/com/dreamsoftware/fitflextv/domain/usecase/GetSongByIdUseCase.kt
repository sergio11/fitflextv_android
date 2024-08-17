package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.TrainingSongBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingSongsRepository
import com.dreamsoftware.fudge.core.FudgeUseCaseWithParams

class GetSongByIdUseCase(
    private val trainingSongRepository: ITrainingSongsRepository
) : FudgeUseCaseWithParams<GetSongByIdUseCase.Params, TrainingSongBO>() {

    override suspend fun onExecuted(params: Params): TrainingSongBO =
        trainingSongRepository.getSongById(params.id)

    data class Params(
        val id: String
    )
}