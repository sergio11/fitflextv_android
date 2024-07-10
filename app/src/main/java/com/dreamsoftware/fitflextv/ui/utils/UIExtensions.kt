package com.dreamsoftware.fitflextv.ui.utils

import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import kotlin.time.Duration

fun Number.padStartWith0(): String = this.toString().padStart(2, '0')

fun TrainingBO?.formatTimeAndTypeTraining(): String =
    this?.run { "$time | $type ••••" }.orEmpty()

fun Duration.toVideoTextProgress() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m.padStartWith0()}:${s.padStartWith0()}"
    } else {
        "${m.padStartWith0()}:${s.padStartWith0()}"
    }
}

fun Duration.toVideoTextDuration() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m.padStartWith0()}:${s.padStartWith0()}"
    } else {
        "${m.padStartWith0()}:${s.padStartWith0()}"
    }
}


fun Duration.toAudioTextProgress() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m}:${s.padStartWith0()}"
    } else {
        "${m}:${s.padStartWith0()}"
    }
}

fun Duration.toAudioTextDuration() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m}:${s.padStartWith0()}"
    } else {
        "${m}:${s.padStartWith0()}"
    }
}

val String.Companion.EMPTY: String
    get() = ""

val Char.Companion.SPACE: Char
    get() = ' '


fun TrainingTypeEnum.getStartButtonID() = when (this) {
    TrainingTypeEnum.CHALLENGES -> R.string.start_session
    TrainingTypeEnum.SERIES -> R.string.start_program
    TrainingTypeEnum.WORK_OUT -> R.string.start_workout
    TrainingTypeEnum.ROUTINE -> R.string.start_routine
}

fun TrainingTypeEnum.getSecondaryButtonID() = when (this) {
    TrainingTypeEnum.CHALLENGES -> R.string.add_to_favorites
    TrainingTypeEnum.SERIES -> R.string.recommend_schedule
    else -> R.string.set_up_daily_reminder
}

fun TrainingTypeEnum.getSecondaryButtonIcon() = when (this) {
    TrainingTypeEnum.CHALLENGES -> R.drawable.fav_icon
    TrainingTypeEnum.SERIES -> R.drawable.message_icon
    else -> R.drawable.bell_icon
}

fun TrainingTypeEnum.isSecondaryButtonVisible() = when (this) {
    TrainingTypeEnum.WORK_OUT -> false
    else -> true
}