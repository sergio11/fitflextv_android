package com.dreamsoftware.fitflextv.data.entities

import java.util.Date

data class Series(
    val id: String,
    val name: String,
    val description: String,
    val instructorName: String,
    val workoutType: com.dreamsoftware.fitflextv.data.entities.WorkoutType,
    val imageUrl: String,
    val numberOfWeeks: Long,
    val numberOfClasses: Int,
    val minutesPerDay: Int,
    val videoUrl: String,
    val intensity: com.dreamsoftware.fitflextv.data.entities.Intensity,
    val releasedDate: Date,
    val language: com.dreamsoftware.fitflextv.data.entities.Language,
    val subtitleLanguage: com.dreamsoftware.fitflextv.data.entities.SubtitleLanguage
)
