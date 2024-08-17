package com.dreamsoftware.fitflextv.ui.utils

import android.content.Context
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO

fun ITrainingProgramBO?.formatTimeAndTypeTraining(): String =
    this?.run { "$duration | $intensity ••••" }.orEmpty()

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

fun SubscriptionBO.formatPeriodTimeAndPrice(periodTime: String, price: String, context: Context): String =
    "${context.getString(R.string.free_trail)} $price / ${
        if (periodTime == "1")
            "${context.getString(R.string.month)}.\n"
        else
            "$periodTime \n${context.getString(R.string.months)}."
    }${context.getString(R.string.subscription_cancelled)}"

fun SubscriptionBO.formatPeriodTime(periodTime: String, context: Context): String =
    "$periodTime ${context.getString(R.string.month_subscription)}"