package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.dreamsoftware.fitflextv.ui.navigation.DashboardNavHost
import com.dreamsoftware.fitflextv.ui.navigation.Screens

@Composable
fun DashboardScreen(
    navController: NavHostController,
    onNavigateTo: (Screens) -> Unit,
    currentDestination: NavDestination?
) {
    DashboardNavigationDrawer(
        modifier = Modifier,
        onNavigateTo = onNavigateTo,
        screens = listOf(
            Screens.Home,
            Screens.Training,
            Screens.Favorite,
            Screens.Settings
        ),
        currentDestination = currentDestination,
    ) {
        DashboardNavHost(navController)
    }
}