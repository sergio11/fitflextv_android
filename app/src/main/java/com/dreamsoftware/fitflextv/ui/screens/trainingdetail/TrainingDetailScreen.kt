package com.dreamsoftware.fitflextv.ui.screens.trainingdetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

data class TrainingDetailScreenArgs(
    val id: String,
    val type: TrainingTypeEnum
)

@Composable
fun TrainingDetailScreen(
    viewModel: TrainingDetailViewModel = hiltViewModel(),
    args: TrainingDetailScreenArgs,
    onOpenTrainingProgram: (String, TrainingTypeEnum) -> Unit,
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { TrainingDetailUiState() },
        onSideEffect = {
            when(it) {
                is TrainingDetailSideEffects.OpenTrainingProgram -> onOpenTrainingProgram(it.id, it.type)
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