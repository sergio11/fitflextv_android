package com.dreamsoftware.fitflextv.ui.screens.profiles.delete

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class DeleteProfileScreenArgs(
    val profileId: String
)

@Composable
fun DeleteProfileScreen(
    args: DeleteProfileScreenArgs,
    viewModel: DeleteProfileViewModel = hiltViewModel(),
    onProfileDeletedSuccessfully: () -> Unit,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInit = { load(args.profileId) },
        onBackPressed = onBackPressed,
        onSideEffect = {
            when(it) {
                DeleteProfileSideEffects.CancelConfiguration -> onBackPressed()
                DeleteProfileSideEffects.ProfileDeleteSuccessfully -> onProfileDeletedSuccessfully()
            }
        },
        onInitialUiState = { DeleteProfileUiState() },
    ) { uiState ->
        DeleteProfileScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}