package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

data class ChallengeBO(
    override val id: String,
    override val name: String,
    override val description: String,
    override val instructorName: String,
    override val workoutType: WorkoutTypeEnum,
    override val imageUrl: String,
    override val duration: String,
    val numberOfDays: Int,
    val weaklyPlans: List<Pair<String, List<WorkoutBO>>>,
    override val intensity: IntensityEnum,
    override val releasedDate: Date,
    override val language: LanguageEnum,
    override val subtitleLanguage: SubtitleLanguageEnum
) : ITrainingProgramBO