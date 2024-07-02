package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

data class RoutineBO (
    val id: String,
    val name: String,
    val description: String,
    val instructorName: String,
    val workoutTypeEnum: WorkoutTypeEnum,
    val imageUrl: String,
    val duration: Long,
    val videoUrl: String,
    val intensityEnum: IntensityEnum,
    val releasedDate: Date,
    val languageBO: LanguageBO,
    val subtitleLanguageBO: SubtitleLanguageBO
)