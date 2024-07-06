package com.dreamsoftware.fitflextv.domain.model

import com.dreamsoftware.fitflextv.utils.HasValue

enum class WorkoutTypeEnum(override val value: String): HasValue {
    YOGA("Yoga"),
    STRENGTH("Strength"),
    SESSIONS("Sessions"),
    CHALLENGE("Challenge"),
    CARDIO("Cardio"),
}