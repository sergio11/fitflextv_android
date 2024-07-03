package com.dreamsoftware.fitflextv.ui.screens.training.trainingentities

import com.dreamsoftware.fitflextv.R



fun TrainingEntityUiState.ContentType.isSecondaryButtonVisible() = when (this) {
    TrainingEntityUiState.ContentType.WORK_OUT -> false
    else -> true
}