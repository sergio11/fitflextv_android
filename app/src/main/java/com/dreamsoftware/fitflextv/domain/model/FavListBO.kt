package com.dreamsoftware.fitflextv.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class FavListBO(
    val value: List<FavWorkout> = emptyList()
): List<FavWorkout> by value
