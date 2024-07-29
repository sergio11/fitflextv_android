package com.dreamsoftware.fitflextv.domain.model

data class AddFavoriteTrainingBO(
    val userId: String,
    val trainingId: String,
    val trainingType: TrainingTypeEnum
)
