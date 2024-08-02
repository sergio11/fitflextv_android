package com.dreamsoftware.fitflextv.domain.model

data class AddFavoriteTrainingBO(
    val profileId: String,
    val trainingId: String,
    val trainingType: TrainingTypeEnum
)
