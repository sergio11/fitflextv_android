package com.dreamsoftware.fitflextv.ui.screens.profiles.secure

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun SecurePinScreen(
    args: SecurePinScreenArgs,
    viewModel: SecurePinViewModel = hiltViewModel(),
    onGoToHome: () -> Unit,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInit = { load(args.profileId) },
        onBackPressed = onBackPressed,
        onInitialUiState = { SecurePinUiState() },
        onSideEffect = {
            when(it) {
                SecurePinSideEffects.CancelVerification -> onBackPressed()
                SecurePinSideEffects.ProfileUnlockedSuccessfully -> onGoToHome()
            }
        }
    ) { uiState ->
        SecurePinScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}