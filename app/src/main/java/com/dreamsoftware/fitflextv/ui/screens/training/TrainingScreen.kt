package com.dreamsoftware.fitflextv.ui.screens.training

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun TrainingScreen(
    viewModel: TrainingViewModel = hiltViewModel(),
    onGoToTrainingDetail: (id: String) -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { TrainingUiState() },
        onSideEffect = {
            when(it) {
                is TrainingSideEffects.OpenTrainingDetail -> onGoToTrainingDetail(it.id)
            }
        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        TrainingScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}


