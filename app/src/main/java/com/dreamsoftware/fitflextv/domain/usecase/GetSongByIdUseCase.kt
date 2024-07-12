package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.SongBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams
import com.dreamsoftware.fitflextv.ui.utils.EMPTY

class GetSongByIdUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCaseWithParams<GetSongByIdUseCase.Params, SongBO>() {
    override suspend fun onExecuted(params: Params): SongBO =
        SongBO(String.EMPTY, String.EMPTY, String.EMPTY, String.EMPTY, String.EMPTY, String.EMPTY)

    data class Params(
        val id: String
    )
}