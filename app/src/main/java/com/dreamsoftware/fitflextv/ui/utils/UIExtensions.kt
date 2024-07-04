package com.dreamsoftware.fitflextv.ui.utils

import com.dreamsoftware.fitflextv.domain.model.TrainingDetailsBO
import kotlin.time.Duration

fun Number.padStartWith0(): String = this.toString().padStart(2, '0')

fun TrainingDetailsBO?.formatTimeAndTypeTraining(): String =
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