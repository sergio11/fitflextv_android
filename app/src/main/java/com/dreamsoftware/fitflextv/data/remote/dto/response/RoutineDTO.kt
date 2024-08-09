package com.dreamsoftware.fitflextv.data.remote.dto.response

import java.util.Date

data class RoutineDTO(
    val id: String,
    val name: String,
    val description: String,
    val instructor: String,
    val workoutType: String,
    val imageUrl: String,
    val duration: String,
    val videoUrl: String,
    val intensity: String,
    val releasedDate: Date,
    val isPremium: Boolean,
    val language: String,
    val song: String,
    val category: String
)
