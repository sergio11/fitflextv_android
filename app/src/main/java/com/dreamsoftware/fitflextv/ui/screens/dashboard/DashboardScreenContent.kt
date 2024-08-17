package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.navigation.DashboardNavHost
import com.dreamsoftware.fitflextv.ui.navigation.Screen
import com.dreamsoftware.fudge.component.FudgeTvNavigationDrawer

@Composable
internal fun DashboardScreenContent(
    uiState: DashboardUiState,
    actionListener: DashboardActionListener,
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    with(uiState) {
        FudgeTvNavigationDrawer(
            modifier = Modifier,
            mainLogoInverseRes = R.drawable.main_logo_inverse,
            hiddenDrawerRoutes = listOf(Screen.VideoPlayer.route, Screen.AudioPlayer.route),
            onItemClicked = actionListener::onMenuItemSelected,
            items = items,
            currentDestination = currentDestination,
        ) {
            DashboardNavHost(navController)
        }
    }
}