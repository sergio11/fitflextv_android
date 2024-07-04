package com.dreamsoftware.fitflextv.ui.screens.trainingdetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun TrainingDetailScreen(
    viewModel: TrainingDetailViewModel = hiltViewModel(),
    onClickStart: () -> Unit,
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { TrainingDetailUiState() },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        TrainingDetailScreenContent(
            state = uiState,
            onClickStart = onClickStart
        )
    }
}