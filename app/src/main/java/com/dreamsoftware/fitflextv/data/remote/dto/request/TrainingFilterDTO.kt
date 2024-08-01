package com.dreamsoftware.fitflextv.data.remote.dto.request

data class TrainingFilterDTO(
    val classLanguage: String?,
    val workoutType: String?,
    val intensity: String?,
    val videoLength: IntRange?
)
