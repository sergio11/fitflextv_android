package com.dreamsoftware.fitflextv.ui.screens.profiles.changesecurepin

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class ChangeSecurePinScreenArgs(
    val profileId: String
)

@Composable
fun ChangeSecurePinScreen(
    args: ChangeSecurePinScreenArgs,
    viewModel: ChangeSecurePinViewModel = hiltViewModel(),
    onGoToDeleteProfile: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInit = { load(args.profileId) },
        onBackPressed = onBackPressed,
        onInitialUiState = { ChangeSecurePinUiState() },
        onSideEffect = {
            when(it) {
                is ChangeSecurePinSideEffects.RequestDeleteProfile -> onGoToDeleteProfile(it.id)
                ChangeSecurePinSideEffects.SecurePinUpdated -> onBackPressed()
            }
        }
    ) { uiState ->
        ChangeSecurePinScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}