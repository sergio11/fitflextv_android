package com.dreamsoftware.fitflextv.ui.utils

import com.dreamsoftware.fitflextv.domain.model.TrainingDetailsBO
import kotlin.time.Duration

fun Number.padStartWith0(): String = this.toString().padStart(2, '0')

fun TrainingDetailsBO?.formatTimeAndTypeTraining(): String =
    this?.run { "$time | $type ••••" }.orEmpty()

fun Duration.toTextProgress() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m.padStartWith0()}:${s.padStartWith0()}"
    } else {
        "${m.padStartWith0()}:${s.padStartWith0()}"
    }
}

fun Duration.toTextDuration() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m.padStartWith0()}:${s.padStartWith0()}"
    } else {
        "${m.padStartWith0()}:${s.padStartWith0()}"
    }
}