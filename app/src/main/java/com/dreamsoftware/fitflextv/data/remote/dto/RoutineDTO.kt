package com.dreamsoftware.fitflextv.data.remote.dto

import java.util.Date

data class RoutineDTO(
    val id: String,
    val name: String,
    val description: String,
    val instructorName: String,
    val workoutType: String,
    val imageUrl: String,
    val duration: Long,
    val videoUrl: String,
    val intensity: String,
    val releasedDate: Date,
    val language: String,
    val subtitleLanguage: String
)