package com.dreamsoftware.fitflextv.data.remote.dto.response

import java.util.Date

data class SeriesDTO(
    val id: String,
    val name: String,
    val description: String,
    val instructor: String,
    val workoutType: String,
    val imageUrl: String,
    val numberOfWeeks: Long,
    val numberOfClasses: Long,
    val duration: String,
    val videoUrl: String,
    val isPremium: Boolean,
    val intensity: String,
    val releasedDate: Date,
    val language: String,
    val song: String,
    val category: String
)
