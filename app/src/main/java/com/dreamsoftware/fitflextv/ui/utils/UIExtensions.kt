package com.dreamsoftware.fitflextv.ui.utils

import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration

fun Number.padStartWith0(): String = this.toString().padStart(2, '0')

fun ITrainingProgramBO?.formatTimeAndTypeTraining(): String =
    this?.run { "$duration | $intensity ••••" }.orEmpty()

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

suspend fun <T, R> List<T>.parallelMap(
    context: CoroutineContext = Dispatchers.Default,
    block: suspend (T) -> R
): List<R> =
    map { item ->
        coroutineScope {
            async(context) {
                block(item)
            }
        }
    }.awaitAll()

fun ITrainingProgramBO.toTrainingType(): TrainingTypeEnum = when(this) {
    is WorkoutBO -> TrainingTypeEnum.WORK_OUT
    is SeriesBO -> TrainingTypeEnum.SERIES
    is ChallengeBO -> TrainingTypeEnum.CHALLENGES
    else -> TrainingTypeEnum.ROUTINE
}

