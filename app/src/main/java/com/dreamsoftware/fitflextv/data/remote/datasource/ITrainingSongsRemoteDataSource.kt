package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.response.TrainingSongDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchTrainingSongByIdRemoteException

interface ITrainingSongsRemoteDataSource {

    @Throws(FetchTrainingSongByIdRemoteException::class)
    suspend fun getSongById(songId: String): TrainingSongDTO
}