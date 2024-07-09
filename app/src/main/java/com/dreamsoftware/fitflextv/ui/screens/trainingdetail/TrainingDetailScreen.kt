package com.dreamsoftware.fitflextv.ui.screens.trainingdetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

data class TrainingDetailScreenArgs(val id: String)

@Composable
fun TrainingDetailScreen(
    viewModel: TrainingDetailViewModel = hiltViewModel(),
    args: TrainingDetailScreenArgs,
    onClickStart: () -> Unit,
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { TrainingDetailUiState() },
        onInit = {
            fetchData(args.id)
        }
    ) { uiState ->
        TrainingDetailScreenContent(
            state = uiState,
            onClickStart = onClickStart
        )
    }
}