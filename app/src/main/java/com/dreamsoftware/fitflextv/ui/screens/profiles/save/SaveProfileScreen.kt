package com.dreamsoftware.fitflextv.ui.screens.profiles.save

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class SaveProfileScreenArgs(
    val profileId: String
)

@Composable
fun SaveProfileScreen(
    args: SaveProfileScreenArgs? = null,
    viewModel: SaveProfileViewModel = hiltViewModel(),
    onGoToAdvanceConfiguration: (String) -> Unit = {},
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInit = { args?.profileId?.let(::load) },
        onBackPressed = onBackPressed,
        onInitialUiState = { SaveProfileUiState() },
        onSideEffect = {
            when(it) {
                SaveProfileSideEffects.CancelConfiguration -> onBackPressed()
                is SaveProfileSideEffects.OpenAdvanceConfiguration -> onGoToAdvanceConfiguration(it.profileId)
                SaveProfileSideEffects.SaveProfileSuccessfully -> onBackPressed()
            }
        }
    ) { uiState ->
        SaveProfileScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}