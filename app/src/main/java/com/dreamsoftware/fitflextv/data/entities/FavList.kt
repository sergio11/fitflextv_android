package com.dreamsoftware.fitflextv.data.entities

import androidx.compose.runtime.Immutable

@Immutable
data class FavList(
    val value: List<FavWorkout> = emptyList()
): List<FavWorkout> by value
