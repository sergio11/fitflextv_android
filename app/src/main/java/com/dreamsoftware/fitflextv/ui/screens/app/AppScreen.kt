package com.dreamsoftware.fitflextv.ui.screens.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dreamsoftware.fitflextv.ui.navigation.Screen
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fitflextv.ui.utils.navigateSingleTopTo
import com.dreamsoftware.fudge.component.FudgeTvScreen
import com.dreamsoftware.fudge.utils.openSystemSettings
import com.dreamsoftware.fudge.utils.restartApplication

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun AppScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = hiltViewModel()
) {
    FitFlexTVTheme {
        with(LocalContext.current) {
            FudgeTvScreen(
                viewModel = viewModel,
                onInitialUiState = { AppUiState() },
                onSideEffect = {
                    with(navController) {
                        when(it) {
                            AppSideEffects.ComeFromStandby -> navigateSingleTopTo(Screen.ProfileSelector.route)
                            AppSideEffects.NoSessionActive -> navigateSingleTopTo(Screen.Onboarding.route)
                            AppSideEffects.OpenSettings -> openSystemSettings()
                            AppSideEffects.RestartApp -> restartApplication()
                        }
                    }
                }
            ) {
                AppScreenContent(
                    uiState = it,
                    navController = navController,
                    actionListener = viewModel
                )
            }
        }
    }
}