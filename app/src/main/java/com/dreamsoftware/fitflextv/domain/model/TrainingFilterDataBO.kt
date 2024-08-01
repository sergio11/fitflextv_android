package com.dreamsoftware.fitflextv.domain.model

data class TrainingFilterDataBO(
    val type: TrainingTypeEnum,
    val classLanguage: ClassLanguageEnum,
    val workoutType: WorkoutTypeEnum,
    val intensity: IntensityEnum,
    val videoLength: VideoLengthEnum
)
