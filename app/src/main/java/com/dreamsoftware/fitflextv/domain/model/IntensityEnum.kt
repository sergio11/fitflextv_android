package com.dreamsoftware.fitflextv.domain.model

import com.dreamsoftware.fitflextv.ui.utils.EMPTY

enum class IntensityEnum(val value: String, val level: String) {
    NOT_SET("Not set", String.EMPTY),
    EASY("Easy", "Intensity •"),
    MEDIUM("Medium", "Intensity ••"),
    HARD("Hard", "Intensity •••")
}