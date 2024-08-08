package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingSongsByIdException
import com.dreamsoftware.fitflextv.domain.model.TrainingSongBO

interface ITrainingSongsRepository {

    @Throws(FetchTrainingSongsByIdException::class)
    suspend fun getSongById(id: String): TrainingSongBO
}