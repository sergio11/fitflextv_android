package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.dreamsoftware.fitflextv.ui.navigation.DashboardNavHost

@Composable
internal fun DashboardScreenContent(
    uiState: DashboardUiState,
    actionListener: DashboardActionListener,
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    with(uiState) {
        DashboardNavigationDrawer(
            modifier = Modifier,
            onItemClicked = actionListener::onMenuItemSelected,
            items = items,
            currentDestination = currentDestination,
        ) {
            DashboardNavHost(navController)
        }
    }
}