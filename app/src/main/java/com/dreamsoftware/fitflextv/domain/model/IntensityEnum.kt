package com.dreamsoftware.fitflextv.domain.model

import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fitflextv.utils.HasValue

enum class IntensityEnum(override val value: String, val level: String): HasValue {
    NOT_SET("Not set", String.EMPTY),
    EASY("Easy", "Intensity •"),
    MEDIUM("Medium", "Intensity ••"),
    HARD("Hard", "Intensity •••")
}