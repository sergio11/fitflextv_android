package com.dreamsoftware.fitflextv.ui.screens.training

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun TrainingScreen(
    viewModel: TrainingViewModel = hiltViewModel(),
    onGoToTrainingProgramDetail: (String, TrainingTypeEnum) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInitialUiState = { TrainingUiState() },
        onSideEffect = {
            when(it) {
                is TrainingSideEffects.OpenTrainingProgramDetail -> onGoToTrainingProgramDetail(it.id, it.type)
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


