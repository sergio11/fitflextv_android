package com.dreamsoftware.fitflextv.ui.screens.more_options

import com.dreamsoftware.fitflextv.domain.model.TrainingDetailsBO

sealed interface MoreOptionsUiState {
    data object Loading : MoreOptionsUiState
    data object Error : MoreOptionsUiState
    data class Ready(
        val trainingDetailsBO: TrainingDetailsBO
    ) : MoreOptionsUiState {
        fun formatTimeAndTypeTraining(): String {
            return "${trainingDetailsBO.time} | ${trainingDetailsBO.type} ••••"
        }
    }
}
