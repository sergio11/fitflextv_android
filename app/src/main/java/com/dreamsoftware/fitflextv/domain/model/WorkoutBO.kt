package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

data class WorkoutBO(
    override val id: String,
    override val name: String,
    override val description: String,
    override val instructorName: String,
    override val instructorId: String,
    override val workoutType: WorkoutTypeEnum,
    override val imageUrl: String,
    override val videoUrl: String,
    override val intensity: IntensityEnum,
    override val releasedDate: Date,
    override val language: LanguageEnum,
    override val song: String,
    override val isPremium: Boolean,
    override val duration: String
) : ITrainingProgramBO