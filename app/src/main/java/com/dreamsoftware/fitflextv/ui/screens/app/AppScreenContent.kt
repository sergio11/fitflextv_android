package com.dreamsoftware.fitflextv.ui.screens.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreenContent
import com.dreamsoftware.fitflextv.ui.navigation.AppNavHost
import com.dreamsoftware.fitflextv.ui.theme.LocalNavigationProvider

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun AppScreenContent(
    uiState: AppUiState,
    navController: NavHostController,
    onOpenSettingsPressed: () -> Unit,
    onRestartAppPressed: () -> Unit,
    onErrorAccepted: () -> Unit,
) {
    with(uiState) {
        CommonScreenContent(
            error = errorMessage,
            onErrorAccepted = onErrorAccepted
        ) {
            CompositionLocalProvider(LocalNavigationProvider provides navController) {
                AppNavHost(
                    navController = navController
                )
            }
        }
    }
}