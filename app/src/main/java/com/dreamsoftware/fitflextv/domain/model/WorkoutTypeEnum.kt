package com.dreamsoftware.fitflextv.domain.model

import com.dreamsoftware.fitflextv.utils.HasValue

enum class WorkoutTypeEnum(override val value: String): HasValue {
    NOT_SET("Not set"),
    YOGA("Yoga"),
    STRENGTH("Strength"),
    CARDIO("Cardio")
}