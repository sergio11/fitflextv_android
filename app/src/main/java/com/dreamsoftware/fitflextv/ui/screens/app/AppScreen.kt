package com.dreamsoftware.fitflextv.ui.screens.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen
import com.dreamsoftware.fitflextv.ui.navigation.Screen
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fitflextv.ui.utils.navigateSingleTopTo

@Composable
fun AppScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = hiltViewModel()
) {
    FitFlexTVTheme {
        CommonScreen(
            viewModel = viewModel,
            onInitialUiState = { AppUiState() },
            onSideEffect = {
                with(navController) {
                    val destination: String = when(it) {
                        AppSideEffects.ComeFromStandby -> Screen.ProfileSelector.route
                        AppSideEffects.NoSessionActive -> Screen.Onboarding.route
                    }
                    navigateSingleTopTo(destination)
                }
            }
        ) {
            with(LocalContext.current) {
                AppScreenContent(
                    uiState = it,
                    navController = navController,
                    onOpenSettingsPressed = {

                    },
                    onRestartAppPressed = {

                    },
                    onErrorAccepted = ::onErrorAccepted
                )
            }
        }
    }
}