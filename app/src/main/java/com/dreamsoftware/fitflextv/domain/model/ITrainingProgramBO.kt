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
    val isPremium: Boolean
    val language: LanguageEnum
    val videoUrl: String
    val song: String
    val duration: String
}