package com.dreamsoftware.fitflextv.data.remote.dto

import java.util.Date

data class ChallengeDTO(
    val id: String,
    val name: String,
    val description: String,
    val instructorName: String,
    val workoutType: String,
    val imageUrl: String,
    val duration: String,
    val videoUrl: String,
    val intensity: String,
    val releasedDate: Date,
    val language: String,
    val numberOfDays: Long,
    val category: String,
    val weaklyPlans: List<ChallengeWeaklyPlansDTO>
)

data class ChallengeWeaklyPlansDTO(
    val name: String,
    val workouts: List<String>
)
