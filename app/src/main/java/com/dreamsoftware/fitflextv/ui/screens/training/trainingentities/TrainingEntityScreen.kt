package com.dreamsoftware.fitflextv.ui.screens.training.trainingentities

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun TrainingEntityScreen(
    viewModel: TrainingEntityViewModel = hiltViewModel(),
    onClickStart: () -> Unit,
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { TrainingEntityUiState() },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        TrainingEntityScreenContent(
            state = uiState,
            onClickStart = onClickStart
        )
    }
}