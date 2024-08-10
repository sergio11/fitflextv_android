package com.dreamsoftware.fitflextv.ui.utils

import android.content.Context
import android.content.Intent
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
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
import android.provider.Settings
import com.dreamsoftware.fitflextv.domain.model.ClassLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.model.VideoLengthEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.ui.screens.training.FilterTypeEnum
import com.dreamsoftware.fitflextv.ui.screens.training.TrainingFilterVO

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


fun AvatarTypeEnum.toDrawableResource(): Int =
    when(this) {
        AvatarTypeEnum.BOY -> R.drawable.profile_avatar_boy
        AvatarTypeEnum.GIRL -> R.drawable.profile_avatar_girl
        AvatarTypeEnum.WOMAN -> R.drawable.profile_avatar_woman
        AvatarTypeEnum.MAN -> R.drawable.profile_avatar_man
    }

fun Context.restartApplication() {
    packageManager.getLeanbackLaunchIntentForPackage(packageName)?.run {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }?.let(::startActivity)
}

fun Context.openSystemSettings() {
    startActivity(Intent(Settings.ACTION_SETTINGS))
}


fun SubscriptionBO.formatPeriodTimeAndPrice(periodTime: String, price: String, context: Context): String =
    "${context.getString(R.string.free_trail)} $price / ${
        if (periodTime == "1")
            "${context.getString(R.string.month)}.\n"
        else
            "$periodTime \n${context.getString(R.string.months)}."
    }${context.getString(R.string.subscription_cancelled)}"

fun SubscriptionBO.formatPeriodTime(periodTime: String, context: Context): String =
    "$periodTime ${context.getString(R.string.month_subscription)}"

fun List<TrainingFilterVO>.resetOptions() = map { item ->
    item.copy(
        selectedOption = 0,
        description = when(item.type) {
            FilterTypeEnum.VIDEO_LENGTH -> VideoLengthEnum.NOT_SET.value
            FilterTypeEnum.CLASS_TYPE -> WorkoutTypeEnum.NOT_SET.value
            FilterTypeEnum.DIFFICULTY -> IntensityEnum.NOT_SET.value
            FilterTypeEnum.CLASS_LANGUAGE -> ClassLanguageEnum.NOT_SET.value
            FilterTypeEnum.INSTRUCTOR -> String.EMPTY
        }
    )
}