package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

data class ChallengeBO(
    override val id: String,
    override val name: String,
    override val description: String,
    override val instructorName: String,
    override val instructorId: String,
    override val workoutType: WorkoutTypeEnum,
    override val imageUrl: String,
    override val duration: String,
    override val videoUrl: String,
    val numberOfDays: Long,
    val weaklyPlans: List<ChallengeWeaklyPlansBO>,
    override val intensity: IntensityEnum,
    override val releasedDate: Date,
    override val song: String,
    override val isPremium: Boolean,
    override val language: LanguageEnum
) : ITrainingProgramBO

data class ChallengeWeaklyPlansBO(
    val name: String,
    val workouts: List<WorkoutBO>
)