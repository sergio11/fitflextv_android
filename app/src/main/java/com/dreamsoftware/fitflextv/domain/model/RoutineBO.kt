package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

data class RoutineBO(
    override val id: String,
    override val name: String,
    override val description: String,
    override val instructorName: String,
    override val workoutType: WorkoutTypeEnum,
    override val imageUrl: String,
    override val duration: String,
    override val videoUrl: String,
    override val intensity: IntensityEnum,
    override val releasedDate: Date,
    override val isPremium: Boolean,
    override val song: String,
    override val language: LanguageEnum
) : ITrainingProgramBO
