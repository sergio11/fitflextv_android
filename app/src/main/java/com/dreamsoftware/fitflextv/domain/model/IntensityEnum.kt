package com.dreamsoftware.fitflextv.domain.model

import com.dreamsoftware.fitflextv.utils.HasValue

enum class IntensityEnum(override val value: String, val level: String): HasValue {
    EASY("Easy", "Intensity •"),
    MEDIUM("Medium", "Intensity ••"),
    HARD("Hard", "Intensity •••")
}