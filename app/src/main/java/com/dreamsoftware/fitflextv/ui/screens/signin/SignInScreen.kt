package com.dreamsoftware.fitflextv.ui.screens.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onGoToHome: () -> Unit,
    onGoToProfiles: () -> Unit,
    onGoToSignUp: () -> Unit,
    onBackPressed: () -> Unit,
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { SignInUiState() },
        onSideEffect = {
            when(it) {
                SignInSideEffects.AuthenticationSuccessfully -> onGoToHome()
                SignInSideEffects.ProfileSelectionRequired -> onGoToProfiles()
            }
        }
    ) { uiState ->
        SignInScreenContent(
            uiState = uiState,
            onEmailChanged = ::onEmailChanged,
            onPasswordChanged = ::onPasswordChanged,
            onErrorAcceptPressed = ::onErrorAccepted,
            onSigInPressed = ::onSignIn,
            onGoToSignUp = onGoToSignUp
        )
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignInScreenPrev() {
    FitFlexTVTheme {
        SignInScreen(
            onGoToHome = {},
            onGoToSignUp = {},
            onGoToProfiles = {},
            onBackPressed = {}
        )
    }
}