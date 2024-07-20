package com.dreamsoftware.fitflextv.data.remote.dto.request

data class AddFavoriteTrainingDTO(
    val userId: String,
    val trainingId: String,
    val trainingType: String
)
