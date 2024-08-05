package com.dreamsoftware.fitflextv.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel = hiltViewModel(),
    onGoToOnboarding: () -> Unit,
    onGoToHome: () -> Unit,
    onGoToProfileSelector: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { SplashUiState() },
        onSideEffect = {
            when(it) {
                SplashSideEffects.UserAlreadyAuthenticated -> onGoToHome()
                SplashSideEffects.UserNotAuthenticated -> onGoToOnboarding()
                SplashSideEffects.ProfileSelectionRequired -> onGoToProfileSelector()
            }
        },
        onInit = {
            verifySession()
        }
    ) { uiState ->
        SplashScreenContent(
            modifier = modifier,
            uiState = uiState
        )
    }
}