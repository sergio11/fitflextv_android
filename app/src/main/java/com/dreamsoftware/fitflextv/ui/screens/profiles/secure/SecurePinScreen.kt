package com.dreamsoftware.fitflextv.ui.screens.profiles.secure

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun SecurePinScreen(
    args: SecurePinScreenArgs,
    viewModel: SecurePinViewModel = hiltViewModel(),
    onGoToHome: () -> Unit,
    onBackPressed: () -> Unit
) {
    CommonScreen(
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