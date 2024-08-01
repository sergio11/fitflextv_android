package com.dreamsoftware.fitflextv.domain.model

enum class VideoLengthEnum(val value: String, val range: IntRange?) {
    NOT_SET("Not set", null),
    SHORT("10-30 min", 10..30),
    MEDIUM("30-45 min", 30..45),
    LONG("45-60 min", 45..60)
}