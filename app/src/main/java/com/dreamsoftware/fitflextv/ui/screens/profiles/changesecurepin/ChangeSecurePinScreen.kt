package com.dreamsoftware.fitflextv.ui.screens.profiles.changesecurepin

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

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
    CommonScreen(
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