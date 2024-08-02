package com.dreamsoftware.fitflextv.ui.screens.profiles.advance

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

data class ProfileAdvanceScreenArgs(
    val profileId: String
)

@Composable
fun ProfileAdvanceScreen(
    args: ProfileAdvanceScreenArgs,
    viewModel: ProfileAdvanceViewModel = hiltViewModel(),
    onGoToDeleteProfile: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = { load(args.profileId) },
        onBackPressed = onBackPressed,
        onInitialUiState = { ProfileAdvanceUiState() },
    ) { uiState ->
        ProfileAdvanceScreenContent(
            uiState = uiState,
            onConfirmPressed = onBackPressed,
            onDeleteProfilePressed = {
                onGoToDeleteProfile(args.profileId)
            },
            onNewTabSelected = ::onNewTabSelected,
            onErrorAccepted = ::onErrorAccepted
        )
    }
}