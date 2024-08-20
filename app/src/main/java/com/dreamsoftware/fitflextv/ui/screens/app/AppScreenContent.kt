package com.dreamsoftware.fitflextv.ui.screens.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.navigation.AppNavHost
import com.dreamsoftware.fitflextv.ui.theme.LocalNavigationProvider
import com.dreamsoftware.fudge.component.FudgeTvLostNetworkConnectivityDialog
import com.dreamsoftware.fudge.component.FudgeTvScreenContent

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
internal fun AppScreenContent(
    uiState: AppUiState,
    navController: NavHostController,
    actionListener: IAppScreenActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(
            error = errorMessage,
            onErrorAccepted = actionListener::onErrorMessageCleared
        ) {
            Box {
                CompositionLocalProvider(LocalNavigationProvider provides navController) {
                    AppNavHost(
                        navController = navController
                    )
                    FudgeTvLostNetworkConnectivityDialog(
                        isVisible = !hasNetworkConnectivity,
                        mainLogoRes = R.drawable.main_logo,
                        onOpenSettings = actionListener::onOpenSettingsPressed,
                        onRestartAppPressed = actionListener::onRestartAppPressed
                    )
                }
            }
        }
    }
}