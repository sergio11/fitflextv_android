package com.dreamsoftware.fitflextv.ui.screens.moreoptions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

data class MoreOptionsScreenArgs(
    val id: String,
    val type: TrainingTypeEnum
)

@Composable
fun MoreOptionsScreen(
    viewModel: MoreOptionsViewModel = hiltViewModel(),
    args: MoreOptionsScreenArgs,
    onBackPressed: () -> Unit,
    onOpenInstructorDetail: (id: String) -> Unit,
    onPlayTrainingProgram: (id: String, type: TrainingTypeEnum) -> Unit,
    onPlayTrainingSong: (songId: String) -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { MoreOptionsUiState() },
        onSideEffect = {
            when(it) {
                MoreOptionsSideEffects.ExitFromMoreDetail -> onBackPressed()
                is MoreOptionsSideEffects.PlayTrainingProgram -> onPlayTrainingProgram(it.id, it.type)
                is MoreOptionsSideEffects.PlayTrainingSong -> onPlayTrainingSong(it.songId)
                is MoreOptionsSideEffects.OpenInstructorDetail -> onOpenInstructorDetail(it.id)
            }
        },
        onInit = {
            with(args) {
                fetchData(id = id, type = type)
            }
        }
    ) { uiState ->
        MoreOptionsScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}

