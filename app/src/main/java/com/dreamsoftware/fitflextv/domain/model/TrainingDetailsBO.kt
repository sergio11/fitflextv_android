package com.dreamsoftware.fitflextv.domain.model

data class TrainingDetailsBO(
    val id: String,
    val instructor: String,
    val type: String,
    val title: String,
    val time: String,
    val imageUrl: String,
    val description: String
)