package com.dreamsoftware.fitflextv.ui.screens.trainingdetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class TrainingDetailScreenArgs(
    val id: String,
    val type: TrainingTypeEnum
)

@Composable
fun TrainingDetailScreen(
    viewModel: TrainingDetailViewModel = hiltViewModel(),
    args: TrainingDetailScreenArgs,
    onPlayingTrainingProgram: (String, TrainingTypeEnum) -> Unit,
    onOpenMoreDetails: (String, TrainingTypeEnum) -> Unit,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { TrainingDetailUiState() },
        onSideEffect = {
            when(it) {
                is TrainingDetailSideEffects.PlayingTrainingProgram -> onPlayingTrainingProgram(it.id, it.type)
                is TrainingDetailSideEffects.OpenMoreInfo -> onOpenMoreDetails(it.id, it.type)
            }
        },
        onInit = {
            fetchData(
                id = args.id,
                type = args.type
            )
        }
    ) { uiState ->
        TrainingDetailScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}