package com.dreamsoftware.fitflextv.ui.utils

import com.dreamsoftware.fitflextv.domain.model.TrainingDetailsBO

fun Number.padStartWith0(): String = this.toString().padStart(2, '0')


fun TrainingDetailsBO?.formatTimeAndTypeTraining(): String = this?.run { "$time | $type ••••" }.orEmpty()