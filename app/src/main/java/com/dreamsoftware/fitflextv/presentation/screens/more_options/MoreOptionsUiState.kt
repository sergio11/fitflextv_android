package com.dreamsoftware.fitflextv.presentation.screens.more_options

import com.dreamsoftware.fitflextv.data.entities.TrainingDetails

sealed interface MoreOptionsUiState {
    data object Loading : MoreOptionsUiState
    data object Error : MoreOptionsUiState
    data class Ready(
        val trainingDetails: com.dreamsoftware.fitflextv.data.entities.TrainingDetails
    ) : MoreOptionsUiState {
        fun formatTimeAndTypeTraining(): String {
            return "${trainingDetails.time} | ${trainingDetails.type} ••••"
        }
    }
}
