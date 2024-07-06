package com.dreamsoftware.fitflextv.domain.model

import kotlinx.serialization.Serializable
import java.util.Date

data class WorkoutBO(
    val id: String,
    val name: String,
    val description: String,
    val instructorName: String,
    val workoutTypeEnum: WorkoutTypeEnum,
    val imageUrl: String,
    val duration: String,
    val videoUrl: String,
    val intensityEnum: IntensityEnum,
    val releasedDate: Date,
    val languageEnum: LanguageEnum,
    val subtitleLanguageEnum: SubtitleLanguageEnum,
    val subtitleUri: String?)

@Serializable
data class Workout(
    val id: String,
    val name: String,
)