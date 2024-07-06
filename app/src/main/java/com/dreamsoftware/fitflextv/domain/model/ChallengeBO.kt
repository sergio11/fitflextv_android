package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

data class ChallengeBO(
    val id: String,
    val name: String,
    val description: String,
    val instructorName: String,
    val workoutTypeEnum: WorkoutTypeEnum,
    val imageUrl: String,
    val minutesPerDay: Int,
    val numberOfDays: Int,
    val weaklyPlans: List<Pair<String, List<WorkoutBO>>>,
    val intensityEnum: IntensityEnum,
    val releasedDate: Date,
    val language: LanguageEnum,
    val subtitleLanguage: SubtitleLanguageEnum
)