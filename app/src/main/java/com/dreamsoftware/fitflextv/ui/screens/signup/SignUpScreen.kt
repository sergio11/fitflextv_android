package com.dreamsoftware.fitflextv.ui.screens.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { SignUpUiState() },
        onSideEffect = {
            if(it is SignUpSideEffects.RegisteredSuccessfully) {
                onBackPressed()
            }
        }
    ) { uiState ->
        SignUpScreenContent(
            uiState = uiState,
            onFirstNameChanged = ::onFirstNameChanged,
            onLastNameChanged = ::onLastNameChanged,
            onEmailChanged = ::onEmailChanged,
            onUsernameChanged = ::onUsernameChanged,
            onPasswordChanged = ::onPasswordChanged,
            onRepeatPasswordChanged = ::onRepeatPasswordChanged,
            onSigUpPressed = ::onSignUp,
            onCancelPressed = onBackPressed,
            onErrorAccepted = ::onErrorMessageCleared
        )
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignUpScreenPrev() {
    FitFlexTVTheme {
        SignUpScreen(
            onBackPressed = {}
        )
    }
}