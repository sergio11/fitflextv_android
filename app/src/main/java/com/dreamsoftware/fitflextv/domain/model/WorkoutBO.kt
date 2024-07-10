package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

data class WorkoutBO(
    override val id: String,
    override val name: String,
    override val description: String,
    override val instructorName: String,
    override val workoutType: WorkoutTypeEnum,
    override val imageUrl: String,
    val videoUrl: String,
    override val intensity: IntensityEnum,
    override val releasedDate: Date,
    override val language: LanguageEnum,
    override val subtitleLanguage: SubtitleLanguageEnum,
    override val duration: String
) : ITrainingProgramBO