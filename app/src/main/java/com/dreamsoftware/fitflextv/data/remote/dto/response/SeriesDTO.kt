package com.dreamsoftware.fitflextv.data.remote.dto.response

import java.util.Date

data class SeriesDTO(
    val id: String,
    val name: String,
    val description: String,
    val instructorName: String,
    val workoutType: String,
    val imageUrl: String,
    val numberOfWeeks: Long,
    val numberOfClasses: Long,
    val duration: String,
    val videoUrl: String,
    val intensity: String,
    val releasedDate: Date,
    val language: String,
    val category: String
)
