package com.dreamsoftware.fitflextv.domain.model

data class TrainingBO(
    val id: String,
    val instructor: String,
    val type: String,
    val title: String,
    val time: String,
    val imageUrl: String,
    val description: String
)