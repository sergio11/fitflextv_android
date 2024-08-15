package com.dreamsoftware.fitflextv.ui.screens.instructordetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

data class InstructorDetailScreenArgs(
    val id: String
)

@Composable
fun InstructorDetailScreen(
    viewModel: InstructorDetailScreenViewModel = hiltViewModel(),
    args: InstructorDetailScreenArgs,
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { InstructorDetailUiState() },
        onSideEffect = {
            when(it) {
                InstructorDetailSideEffects.ExitFromInstructorDetail -> onBackPressed()
            }
        },
        onInit = {
            fetchData(args.id)
        }
    ) { uiState ->
        InstructorDetailScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}