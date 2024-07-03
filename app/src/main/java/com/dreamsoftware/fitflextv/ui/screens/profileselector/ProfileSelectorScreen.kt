package com.dreamsoftware.fitflextv.ui.screens.profileselector

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun ProfileSelectorScreen(
    viewModel: ProfileSelectorViewModel = hiltViewModel(),
    onGoToSignIn: () -> Unit,
    onGoToDashboard: () -> Unit
) {
    val profileFocusRequester = remember { FocusRequester() }
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { ProfileSelectorUiState() },
        onSideEffect = {

        },
        onInit = {
            getUserProfiles()
        }
    ) { uiState ->
        ProfileSelectorContent(
            state = uiState,
            profileFocusRequester = profileFocusRequester,
            onSignInClick = onGoToSignIn,
            onProfileSelectedClick = {
                onGoToDashboard()
            },
        )
    }
}



