package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

data class SeriesBO(
    override val id: String,
    override val name: String,
    override val description: String,
    override val instructorName: String,
    override val instructorId: String,
    override val workoutType: WorkoutTypeEnum,
    override val imageUrl: String,
    val numberOfWeeks: Long,
    val numberOfClasses: Long,
    override val duration: String,
    override val videoUrl: String,
    override val intensity: IntensityEnum,
    override val releasedDate: Date,
    override val song: String,
    override val isPremium: Boolean,
    override val language: LanguageEnum,
) : ITrainingProgramBO
