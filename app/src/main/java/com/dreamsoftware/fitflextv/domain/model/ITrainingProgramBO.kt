package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

interface ITrainingProgramBO {
    val id: String
    val name: String
    val description: String
    val instructorName: String
    val workoutType: WorkoutTypeEnum
    val imageUrl: String
    val intensity: IntensityEnum
    val releasedDate: Date
    val language: LanguageEnum
    val duration: String
    val subtitleLanguage: SubtitleLanguageEnum
}