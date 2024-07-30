package com.dreamsoftware.fitflextv.domain.model

data class TrainingFilterDataBO(
    val type: TrainingTypeEnum,
    val classLanguage: ClassLanguageEnum,
    val classType: ClassTypeEnum,
    val difficulty: DifficultyEnum,
    val videoLength: VideoLengthEnum
)
